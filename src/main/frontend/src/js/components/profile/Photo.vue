<template>
    <div>
        <div class="flex justify-between">
            <p class="font-light">{{ photo.createdAt | luxonRelative }}</p>
            <IconLink @click="setAvatar(photo)" class="font-light" v-if="user.id == auth.id && (!auth.avatar || auth.avatar.id != photo.id)">set as avatar</IconLink>
            <font-awesome-icon @click="destroy(photo.id)" class="text-gray-300 hover:text-gray-400 cursor-pointer" :icon="['fas', 'times']" v-if="user.id == auth.id && (!auth.avatar || auth.avatar.id != photo.id)" />              
        </div>
        <a :href="`/api/users/${user.slug}/photos/${photo.id}`" target="_blank" rel="noopener noreferrer">
            <img class="py-3" :src="`/api/users/${user.slug}/photos/${photo.id}`" :alt="photo.description" />
        </a>
        <p>{{ photo.description }}</p>
        <div class="w-full flex justify-between pt-2">
            <Likes :user="user" :item="photo" v-model="photo.likes" type="photo" />
            <div v-if="commentButton">
                <IconButton @click="$emit('open')" :icon="['fas', 'comment-alt']" />
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        user: Object,
        photo: Object,
        commentButton: Boolean
    },
    data: function() {
        return {
            auth: globalThis.auth,
            photos: null,
            description: null
        }
    },
    methods: {
        setAvatar: function(photo) {
            if (confirm("Are you sure you want to set this photo as your avatar?"))
                wretch(`/api/users/${this.user.slug}/avatar?id=${photo.id}`).put().res(res => window.location.reload());
        },
        destroy: function(id) {
            if (confirm("Are you sure you want to delete this photo?"))
                wretch(`/api/users/${this.user.slug}/photos/${id}`).delete().res(res => this.$emit('destroy'));
        }
    }
}
</script>
