<template>
    <div class="flex items-center">
        <img class="h-10 w-10 rounded-full mr-2" :src="user.avatar ? `/api/users/${user.slug}/photos/${user.avatar.id}/square` : '/img/profile.svg'" alt="">
        <div class="flex flex-col">
            <router-link class="" :to="`/profiles/${user.slug}`">{{ user.name }}</router-link>
            <span class="text-sm font-light">{{ createdAt | luxonRelative }}</span>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        user: Object,
        createdAt: String,
    }
}
</script>
