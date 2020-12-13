<template>
    <div v-if="user && photo">
        <Card>
            <Photo :user="user" :photo="photo" />
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
            photo: null
        }
    },
    methods: {
        load: function() {
            wretch(`/api/users/${this.$route.params.slug}/photos/${this.$route.params.id}/json`).get().json(json => this.photo = json);
        }
    },
    beforeMount: function() {
        this.load();
    }
}
</script>
