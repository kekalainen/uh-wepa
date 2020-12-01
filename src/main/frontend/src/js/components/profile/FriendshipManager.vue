<template>
    <div v-if="friendship != null">
        <div v-if="friendship == false">
            <Button @click="request">Add friend</Button>
        </div>
        <div v-else>
            <div v-if="friendship.accepted">
                <Button class="btn-secondary" @click="destroy">Unfriend</Button>
            </div>
            <div v-else>
                <div class="flex flex-col items-end" v-if="friendship.requester.slug == user.slug">
                    <p class="mb-2">{{ user.name }} requested you as a friend {{ friendship.createdAt | luxonRelative }}.</p>
                    <div>
                        <Button @click="accept">Accept</Button>
                        <Button class="btn-secondary" @click="destroy">Reject</Button>
                    </div>
                </div>
                <div v-else>
                    <Button class="btn-secondary" @click="destroy">Cancel</Button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    model: {
        prop: 'friendship',
        event: 'change'
    },
    props: {
        user: Object,
        friendship: Object
    },
    methods: {
        load: function() {
            wretch(`/api/friends/${this.user.slug}`).get().notFound(e => this.$emit('change', false)).json(json => this.$emit('change', json));
        },
        request: function() {
            wretch(`/api/friends/${this.user.slug}`).post().json(json => this.$emit('change', json));
        },
        accept: function() {
            wretch(`/api/friends/${this.user.slug}`).put().json(json => this.$emit('change', json));
        },
        destroy: function() {
            wretch(`/api/friends/${this.user.slug}`).delete().res(res => this.$emit('change', false));
        }
    },
    beforeMount: function() {
        this.load();
    }
}
</script>
