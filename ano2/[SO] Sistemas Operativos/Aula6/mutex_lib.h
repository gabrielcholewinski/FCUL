#ifndef MUTEX_LIB_H_GUARD
#define MUTEX_LIB_H_GUARD

// Create the mutex.
// On success returns 0; on error, -1 is returned, with errno set to indicate the error.
int init();

// Destroy the mutex.
// On success returns 0; on error, -1 is returned, with errno set to indicate the error.
int destroy();

// Locks the mutex.
// On success returns 0;
// On error, the value of the mutex is left unchanged, -1 is returned, and errno is set to indicate the error.
int lock();

// Unlocks the mutex.
// On success returns 0;
// On error, the value of the mutex is left unchanged, -1 is returned, and errno is set to indicate the error.
int unlock();
#endif
