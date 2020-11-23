<template>
    <p @click="$emit('click')" class="text-xs font-bold text-center text-purple-500 hover:text-purple-700 select-none cursor-pointer">
        <font-awesome-icon class="mr-1" :icon="icon" v-if="icon" />
        <slot />
    </p>
</template>

<script>
export default {
    props: {
        name: String,
        icon: Array
    }
}
</script>
