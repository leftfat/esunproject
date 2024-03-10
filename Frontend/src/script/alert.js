import Swal from 'sweetalert2'

export function successMessage(title) { Swal.fire({ title, icon: 'success' }) }
export function errorMessage(title, text) { Swal.fire({ title, text, icon: 'error' }) }