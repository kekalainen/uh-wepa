
import Vue from 'vue'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

Vue.component('font-awesome-icon', FontAwesomeIcon);

import { library } from '@fortawesome/fontawesome-svg-core';
import { faBook, faTimes, faBars, faBell, faUser, faKey, faPen, faAddressCard, faAt, faSearch } from '@fortawesome/free-solid-svg-icons';

library.add(faBook);
library.add(faTimes);
library.add(faBars);
library.add(faBell);
library.add(faUser);
library.add(faKey);
library.add(faPen);
library.add(faAddressCard);
library.add(faAt);
library.add(faSearch);
