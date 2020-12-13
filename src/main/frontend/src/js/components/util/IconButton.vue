<template>
    <font-awesome-icon
        @click="$emit('click')"
        class="text-purple-300 hover:text-purple-500 cursor-pointer"
        :icon="icon"
    />
</template>

<script>
export default {
    props: {
        icon: Array
    }
}
</script>
