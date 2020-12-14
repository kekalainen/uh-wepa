import Vue from 'vue'

import VueRouter from 'vue-router'
Vue.use(VueRouter)

const files = require.context('./components', true, /\.vue$/i)
files.keys().map(key => Vue.component(key.split('/').pop().split('.')[0], files(key).default))

const Friends = () => import('./pages/Friends.vue');
const People = () => import('./pages/People.vue');
const ProfileLayout = () => import('./pages/profile/Layout.vue');
const Profile = () => import('./pages/profile/Profile.vue');
const Post = () => import('./pages/profile/Post.vue');
const Photo = () => import('./pages/profile/Photo.vue');
import Login from './pages/Login.vue';
const Logout = () => import('./pages/Logout.vue');

const routes = [
    { path: '/', component: Friends, meta: { title: 'Friends' } },
    { path: '/people', name: 'people', component: People, meta: { title: 'People' } },
    { path: '/profiles/:slug', component: ProfileLayout,
        children: [
            { path: '', component: Profile, meta: { title: 'Profile' } },
            { path: '/profiles/:slug/posts/:id', component: Post, meta: { title: 'Post' } },
            { path: '/profiles/:slug/photos/:id', component: Photo, meta: { title: 'Photo' } }
        ]
    },
    { path: '/login', component: Login, meta: { title: 'Login' } },
    { path: '/logout', component: Logout, meta: { title: 'Logout' } }
];

const router = new VueRouter({
    routes,
    mode: 'history'
})

var titleFallback = document.title;
router.beforeEach((to, from, next) => {
    document.title = to.meta.title ?? titleFallback;
    next();
})

new Vue({
    el: '#app',
    router
})
