<template>
    <div>
        <form class="w-full sm:w-1/2 md:w-1/3 flex items-center pl-2 pb-2" @submit.prevent="submit" v-if="user.id == auth.id || (friendship && friendship.accepted)">
            <Input class="w-full" :required="true" :placeholder="`Write something on ${user.name}'s profile`" name="content" v-model="content" />
            <Button class="ml-2">Post</Button>
        </form>
        <div class="flex flex-wrap" v-if="posts">
            <Card class="w-full sm:w-1/2 md:w-1/3 p-2" v-for="post in posts" :key="post.id">
                <div class="flex">
                    <img class="w-1/6 h-1/6 rounded-full mr-4" :src="post.author.avatar ? `/api/users/${post.author.slug}/photos/${post.author.avatar.id}/square` : '/img/profile.svg'" alt="">
                    <div>
                        <div class="flex items-center">
                            <router-link class="text-lg" :to="`/profiles/${post.author.slug}`">{{ post.author.name }}</router-link>
                            <p class="mx-1">&middot;</p><p class="font-light">{{ post.createdAt | luxonRelative }}</p>
                        </div>
                        <p>{{ post.content }}</p>
                    </div>
                </div>
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
            posts: null,
            content: null
        }
    },
    methods: {
        load: function() {
            wretch(`/api/users/${this.user.slug}/posts?size=25&sort=id,DESC`).get().json(json => this.posts = json.content);
        },
        submit: function() {
            wretch(`/api/users/${this.user.slug}/posts`).post({content: this.content}).json(json => this.load());
            this.content = null;
        }
    },
    beforeMount: function() {
        this.load();
    }
}
</script>
