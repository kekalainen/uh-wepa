<template>
    <div class="flex">
        <img class="w-1/6 h-1/6 max-w-16 rounded-full mr-4" :src="post.author.avatar ? `/api/users/${post.author.slug}/photos/${post.author.avatar.id}/square` : '/img/profile.svg'" alt="">
        <div class="w-full">
            <div class="flex items-center">
                <router-link class="text-lg" :to="`/profiles/${post.author.slug}`">{{ post.author.name }}</router-link>
                <p class="mx-1">&middot;</p><p class="font-light">{{ post.createdAt | luxonRelative }}</p>
            </div>
            <p>{{ post.content }}</p>
            <div class="w-full flex justify-between pt-2">
                <Likes :user="user" :item="post" v-model="post.likes" type="post" />
                <div v-if="commentButton">
                    <IconButton @click="$emit('open')" :icon="['fas', 'comment-alt']" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        user: Object,
        post: Object,
        commentButton: Boolean
    }
}
</script>
