<template>
    <div v-if="user && post">
        <Card>
            <Post :user="user" :post="post" />
            <hr class="my-6">
            <Comments :user="user" :item="post" type="post" :friendship="friendship" />
        </Card>
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
            post: null
        }
    },
    methods: {
        load: function() {
            wretch(`/api/users/${this.$route.params.slug}/posts/${this.$route.params.id}`).get().json(json => this.post = json);
        }
    },
    beforeMount: function() {
        this.load();
    }
}
</script>
