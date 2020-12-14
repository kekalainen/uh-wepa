<template>
    <div class="flex">
        <img class="w-1/6 h-1/6 max-w-16 rounded-full mr-4" :src="post.author.avatar ? `/api/users/${post.author.slug}/photos/${post.author.avatar.id}/square` : '/img/profile.svg'" alt="">
        <div class="w-full">
            <div class="flex items-center">
                <router-link class="text-lg" :to="`/profiles/${post.author.slug}`">{{ post.author.name }}</router-link>
                <p class="mx-1">&middot;</p><p class="font-light">{{ post.createdAt | luxonRelative }}</p>
            </div>
            <p class="transition-max-height duration-500" :class="{ 'max-h-full': showMore, 'max-h-24 overflow-hidden': !showMore }">{{ content }}</p>
            <IconButton @click="showMore = !showMore" :icon="['fas', showMore ? 'chevron-up' : 'chevron-down']" v-if="!single && post.content.length > 100" />
            <div class="w-full flex justify-between pt-2">
                <Likes :user="user" :item="post" v-model="post.likes" type="post" />
                <div v-if="!single">
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
        single: Boolean
    },
    data() {
        return {
            showMore: false
        }
    },
    computed: {
        content: function() {
            return this.single || this.showMore ? this.post.content: this.post.content.substring(0, 100);
        }
    }
}
</script>
