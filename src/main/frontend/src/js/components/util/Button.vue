<template>
    <button @click="$emit('click')" class="py-2 px-4 bg-purple-500 text-white font-semibold hover:bg-purple-700 focus:outline-none focus:ring-2 focus:ring-green-400 focus:ring-opacity-75 rounded-md shadow-md">
        <slot />
    </button>
</template>
