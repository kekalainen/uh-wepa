<template>
    <div>
        <form class="w-full flex items-center pl-2 pb-2" @submit.prevent="submit" v-if="user.id == auth.id">
            <input class="w-1/2" id="photo-content" required="true" type="file" name="content" />
            <Input class="w-1/2" :required="true" placeholder="Description" name="description" v-model="description" />
            <Button class="ml-2">Upload</Button>
        </form>
        <div class="flex flex-wrap" v-if="photos">
            <Card class="w-full sm:w-1/2 md:w-1/3 p-2" v-for="photo in photos" :key="photo.id">
                <div class="flex justify-between">
                    <p class="font-light">{{ photo.createdAt | luxonRelative }}</p>
                    <font-awesome-icon @click="destroy(photo.id)" class="text-gray-300 hover:text-gray-400 cursor-pointer" :icon="['fas', 'times']" />
                </div>
                <a :href="`/api/users/${user.slug}/photos/${photo.id}`" target="_blank" rel="noopener noreferrer">
                    <img class="py-3" :src="`/api/users/${user.slug}/photos/${photo.id}`" :alt="photo.description" />
                </a>
                <p>{{ photo.description }}</p>
            </Card>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        user: Object,
        friendship: Object
    },
    data: function() {
        return {
            auth: globalThis.auth,
            photos: null,
            description: null
        }
    },
    methods: {
        load: function() {
            wretch(`/api/users/${this.user.slug}/photos?size=10&sort=id,DESC`).get().json(json => this.photos = json.content);
        },
        submit: function() {
            var content = document.querySelector('#photo-content').files[0];
            if (content.size > 1024 * 1024 * 5)
                return alert('Photo exceeds maximum size of 5 MiB')
            wretch(`/api/users/${this.user.slug}/photos`).formData({content: content, description: this.description}).post().json(json => this.load());
            this.description = null;
        },
        destroy: function(id) {
            if (confirm("Are you sure you want to delete this photo?"))
                wretch(`/api/users/${this.user.slug}/photos/${id}`).delete().res(res => this.load());
        }
    },
    beforeMount: function() {
        this.load();
    }
}
</script>
