<template>
    <div>
        <form class="w-full flex items-center pl-2 pb-2" @submit.prevent="submit" v-if="user.id == auth.id">
            <input class="w-1/2" id="photo-content" required="true" type="file" name="content" />
            <Input class="w-1/2" :required="true" placeholder="Description" name="description" v-model="description" />
            <Button class="ml-2">Upload</Button>
        </form>
        <div v-if="!loading">
            <div class="flex flex-wrap" v-if="photos.length > 0">
                <Card class="w-full sm:w-1/2 md:w-1/3 p-2" v-for="photo in photos" :key="photo.id">
                    <Photo @open="$router.push(`/profiles/${$route.params.slug}/photos/${photo.id}`)" :user="user" :photo="photo" :comment-button="true" />
                </Card>
            </div>
            <p class="text-center" v-else>{{ user.name }} hasn't uploaded any photos.</p>
        </div>
        <LoadingSpinner v-else />
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
            loading: true,
            photos: [],
            description: null
        }
    },
    methods: {
        load: function() {
            this.loading = true;
            wretch(`/api/users/${this.user.slug}/photos?size=10&sort=id,DESC`).get().json(json => {
                this.photos = json.content;
                this.loading = false;
            });
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
