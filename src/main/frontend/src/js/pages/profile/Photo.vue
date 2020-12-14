<template>
    <div v-if="user && photo">
        <Card>
            <Photo :user="user" :photo="photo" v-on:destroy="onDestroy" />
            <hr class="my-6">
            <Comments :user="user" :item="photo" type="photo" :friendship="friendship" />
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
            photo: null
        }
    },
    methods: {
        load: function() {
            wretch(`/api/users/${this.$route.params.slug}/photos/${this.$route.params.id}/json`).get().json(json => this.photo = json);
        },
        onDestroy: function() {
            this.$router.replace('/profiles/' + this.user.slug);
        }
    },
    created() {
        this.load();
        document.title = `Photo by ${this.user.name}`;
    }
}
</script>
