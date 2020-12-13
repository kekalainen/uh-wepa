<template>
    <div>
        <form class="w-full sm:w-1/2 md:w-1/3 flex items-center pl-2 pb-2" @submit.prevent="submit" v-if="user.id == auth.id || (friendship && friendship.accepted)">
            <Input class="w-full" :required="true" :placeholder="`Write something on ${user.name}'s profile`" name="content" v-model="content" />
            <Button class="ml-2">Post</Button>
        </form>
        <div class="flex flex-wrap" v-if="!loading">
            <Card @click="activePost = post.id" class="w-full sm:w-1/2 md:w-1/3 p-2" v-for="post in posts" :key="post.id">
                <Post @open="$router.push(`/profiles/${$route.params.slug}/posts/${post.id}`)" :user="user" :post="post" :comment-button="true" />
            </Card>
        </div>
        <LoadingSpinner v-else />
    </div>
</template>

<script>
export default {
    props: {
        user: Object,
        friendship: [Object, Boolean]
    },
    data: function() {
        return {
            auth: globalThis.auth,
            loading: true,
            activePost: null,
            posts: null,
            content: null
        }
    },
    methods: {
        load: function() {
            this.loading = true;
            wretch(`/api/users/${this.user.slug}/posts?size=25&sort=id,DESC`).get().json(json => {
                this.posts = json.content;
                this.loading = false;
            });
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
