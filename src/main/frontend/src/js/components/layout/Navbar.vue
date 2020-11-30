<template>
    <nav class="bg-purple-500">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div class="flex items-center justify-between h-16">
                <div class="flex items-center">
                    <font-awesome-icon class="fa-2x text-gray-100" :icon="['fas', 'book']" />
                    <div class="hidden md:block ml-10 flex space-x-3">
                        <router-link :to="link.url" class="px-3 py-2 rounded-md text-sm font-medium" :class="$route.path === link.url ? 'text-white bg-purple-700' : 'text-gray-200 hover:text-white hover:bg-purple-600'" v-for="link in links" :key="link.name">{{ link.name }}</router-link>
                    </div>
                </div>
                <!-- desktop -->
                <div class="hidden md:flex items-center">
                    <font-awesome-icon class="text-gray-200 hover:text-white cursor-pointer" :icon="['fas', 'bell']" />
                    <div class="ml-3 relative">
                        <div @click="profileDropdownOpen = !profileDropdownOpen">
                            <button class="max-w-xs rounded-full flex focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-purple-700 focus:ring-white">
                                <img class="h-8 w-8 rounded-full" :src="avatarUrl" alt="">
                            </button>
                        </div>
                        <div @click="profileDropdownOpen = false" :class="{ 'opacity-100 scale-100': profileDropdownOpen, 'opacity-0 scale-95 invisible': !profileDropdownOpen }" class="transform transition-all ease-in-out duration-75 origin-top-right absolute right-0 mt-2 w-48 rounded-md shadow-lg py-1 bg-white ring-1 ring-black ring-opacity-5">
                            <router-link :to="link.url" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100" v-for="link in profileLinks" :key="link.name">{{ link.name }}</router-link>
                        </div>
                    </div>
                </div>
                <!-- mobile -->
                <div class="-mr-2 flex md:hidden">
                    <button @click="menuOpen = !menuOpen" class="inline-flex items-center justify-center p-2 bg-purple-700 hover:bg-purple-600 text-gray-200 hover:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-purple-700 focus:ring-white rounded-md">
                        <font-awesome-icon class="fa-fw" :icon="['fas', menuOpen ? 'times' : 'bars']" />
                    </button>
                </div>
            </div>
        </div>
        <!-- mobile -->
        <div class="md:hidden" :class="{ 'block': menuOpen, 'hidden': !menuOpen }">
            <div class="px-2 pt-2 pb-3 space-y-1 sm:px-3">
                <router-link :to="link.url" class="block px-3 py-2 rounded-md text-base font-medium" :class="$route.path === link.url ? 'text-white bg-purple-700' : 'text-gray-200 hover:text-white hover:bg-purple-600'" v-for="link in links" :key="link.name">{{ link.name }}</router-link>
            </div>
            <div class="pt-4 pb-3 border-t border-gray-700">
                <div class="flex items-center px-5">
                    <div class="flex-shrink-0">
                        <img class="h-10 w-10 rounded-full" :src="avatarUrl" alt="">
                    </div>
                    <div class="ml-3">
                        <div class="font-medium leading-none text-white">{{ auth.name }}</div>
                        <div class="text-sm font-medium text-gray-200">@{{ auth.handle }}</div>
                    </div>
                    <font-awesome-icon class="ml-auto text-gray-200 hover:text-white cursor-pointer" :icon="['fas', 'bell']" />
                </div>
                <div class="mt-3 px-2 space-y-1">
                    <router-link :to="link.url" class="block px-3 py-2 rounded-md text-base font-medium text-gray-200 hover:text-white hover:bg-gray-700" v-for="link in profileLinks" :key="link.name">{{ link.name }}</router-link>
                </div>
            </div>
        </div>
    </nav>
</template>

<script>
export default {
    data() {
        var auth = JSON.parse(localStorage.getItem('auth'));
        return {
            auth: auth,
            menuOpen: false,
            profileDropdownOpen: false,
            avatarUrl: '/img/profile.svg',
            links: [
                {
                    name: 'Friends',
                    url: '/'
                },
                {
                    name: 'People',
                    url: '/people'
                }
            ],
            profileLinks: [
                {
                    name: 'Profile',
                    url: `/profiles/${auth.slug}`
                },
                {
                    name: 'Log out',
                    url: '/logout'
                }
            ]
        }
    }
}
</script>
