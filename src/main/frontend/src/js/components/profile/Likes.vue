<template>
    <p class="pt-2">
        <font-awesome-icon
            @click="like(item)"
            class="text-purple-300 hover:text-purple-500 cursor-pointer"
            :class="{ 'text-purple-500': liked }"
            :icon="['fas', 'thumbs-up']"
        />
        <span class="font-light" v-if="item.likes.length > 0">{{ item.likes[0].author.name }}<span v-if="item.likes.length > 1"> and {{ item.likes.length - 1 }} other{{ item.likes.length != 2 ? 's': '' }}</span></span>
    </p>
</template>

<script>
export default {
    data: function() {
        return {
            auth: globalThis.auth
        }
    },
    props: {
        user: Object,
        item: Object,
        type: String
    },
    computed: {
        liked: function() {
            var liked = false;
            this.item.likes.forEach(like => {
                if (like.author.id == this.auth.id)
                    liked = true;
            });
            return liked;
        }
    },
    methods: {
        like: function(item) {
            if (!this.liked) {
                wretch(`/api/users/${this.user.slug}/${this.type}s/${item.id}/likes`).post().json(json => this.$emit('input', json));
            } else {
                wretch(`/api/users/${this.user.slug}/${this.type}s/${item.id}/likes`).delete().json(json => this.$emit('input', json));
            }
        }
    }
}
</script>
