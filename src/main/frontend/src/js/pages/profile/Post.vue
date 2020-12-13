<template>
    <div v-if="user && post">
        <Card>
            <Post :user="user" :post="post" />
        </Card>
    </div>
</template>

<script>
export default {
    props: {
        user: Object
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
