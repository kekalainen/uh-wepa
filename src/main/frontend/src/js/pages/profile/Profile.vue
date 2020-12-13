<template>
    <div>
        <div class="flex justify-around">
            <Button :class="{ 'btn-secondary': section.name != activeSection }" @click="activeSection = section.name" v-for="section in sections" :key="section.name">
                <font-awesome-icon class="mr-1" :icon="['fas', section.icon]" /> {{ section.title }}
            </Button>
        </div>
        <hr class="my-6">
        <Posts :user="user" :friendship="friendship" v-if="activeSection == 'posts'" />
        <Photos :user="user" v-if="activeSection == 'photos'" />
    </div>
</template>

<script>
export default {
    props: {
        user: Object,
        friendship: [Object, Boolean]
    },
    data() {
        return {
            auth: globalThis.auth,
            activeSection: 'posts',
            sections: [
                {
                    name: 'posts',
                    title: 'Posts',
                    icon: 'pen'
                },
                {
                    name: 'photos',
                    title: 'Photos',
                    icon: 'images'
                }
            ]
        }
    }
}
</script>
