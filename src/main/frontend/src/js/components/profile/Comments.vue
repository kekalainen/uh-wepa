<template>
    <div class="h-full">
        <form class="w-full flex items-center pb-2" @submit.prevent="submit" v-if="user.id == auth.id || (friendship && friendship.accepted)">
            <Input class="w-full" :required="true" placeholder="Write a comment" name="content" v-model="content" />
            <Button class="ml-2">Post</Button>
        </form>
        <div class="max-h-full overflow-y-auto" v-if="!loading">
            <div class="py-2" v-for="comment in comments" :key="comment.id">
                <Author :user="comment.author" :created-at="comment.createdAt" />
                {{ comment.content }}
            </div>
        </div>
        <LoadingSpinner v-else />
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            auth: globalThis.auth,
            loading: true,
            comments: null,
            content: null
        }
    },
    props: {
        user: Object,
        item: Object,
        type: String,
        friendship: [Object, Boolean]
    },
    methods: {
        load: function(item) {
            this.loading = true;
            wretch(`/api/users/${this.user.slug}/${this.type}s/${this.item.id}/comments?size=10&sort=id,DESC`).get().json(json => {
                this.comments = json.content;
                this.loading = false;
            });
        },
        submit: function() {
            wretch(`/api/users/${this.user.slug}/${this.type}s/${this.item.id}/comments`).post({content: this.content}).json(json => this.load());
            this.content = null;
        }
    },
    beforeMount: function() {
        this.load();
    }
}
</script>
