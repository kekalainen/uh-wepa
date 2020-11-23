import wretch from 'wretch';
wretch().defaults({ headers: { 'X-CSRF-TOKEN': document.querySelector("meta[property='X-CSRF-TOKEN']").getAttribute('content') } });
window.wretch = wretch;
