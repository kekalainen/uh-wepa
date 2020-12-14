
import Vue from 'vue'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

Vue.component('font-awesome-icon', FontAwesomeIcon);

import { library } from '@fortawesome/fontawesome-svg-core';
import { faBook, faTimes, faBars, faUser, faKey, faPen, faAddressCard, faAt, faSearch, faImages, faThumbsUp, faCircleNotch, faCommentAlt, faChevronUp, faChevronDown } from '@fortawesome/free-solid-svg-icons';

library.add(faBook, faTimes, faBars, faUser, faKey, faPen, faAddressCard, faAt, faSearch, faImages, faThumbsUp, faCircleNotch, faCommentAlt, faChevronUp, faChevronDown);
