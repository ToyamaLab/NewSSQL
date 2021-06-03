#ifndef BOOST_PTHREAD_MUTEX_SCOPED_LOCK_HPP
#define BOOST_PTHREAD_MUTEX_SCOPED_LOCK_HPP
//  (C) Copyright 2007-8 Anthony Williams
//
//  Distributed under the Boost Software License, Version 1.0. (See
//  accompanying file LICENSE_1_0.txt or copy at
//  http://www.boost.org/LICENSE_1_0.txt)

#include <pthread.h>
#include <boost/assert.hpp>
<<<<<<< HEAD
=======
#include <boost/thread/pthread/pthread_helpers.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#include <boost/config/abi_prefix.hpp>

namespace boost
{
    namespace pthread
    {
        class pthread_mutex_scoped_lock
        {
            pthread_mutex_t* m;
            bool locked;
        public:
            explicit pthread_mutex_scoped_lock(pthread_mutex_t* m_) BOOST_NOEXCEPT:
                m(m_),locked(true)
            {
<<<<<<< HEAD
                BOOST_VERIFY(!pthread_mutex_lock(m));
            }
            void unlock() BOOST_NOEXCEPT
            {
                BOOST_VERIFY(!pthread_mutex_unlock(m));
=======
                BOOST_VERIFY(!posix::pthread_mutex_lock(m));
            }
            void unlock() BOOST_NOEXCEPT
            {
                BOOST_VERIFY(!posix::pthread_mutex_unlock(m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                locked=false;
            }
            void unlock_if_locked() BOOST_NOEXCEPT
            {
              if(locked)
              {
                  unlock();
              }
            }
            ~pthread_mutex_scoped_lock() BOOST_NOEXCEPT
            {
                if(locked)
                {
                    unlock();
                }
            }

        };

        class pthread_mutex_scoped_unlock
        {
            pthread_mutex_t* m;
        public:
            explicit pthread_mutex_scoped_unlock(pthread_mutex_t* m_) BOOST_NOEXCEPT:
                m(m_)
            {
<<<<<<< HEAD
                BOOST_VERIFY(!pthread_mutex_unlock(m));
            }
            ~pthread_mutex_scoped_unlock() BOOST_NOEXCEPT
            {
                BOOST_VERIFY(!pthread_mutex_lock(m));
=======
                BOOST_VERIFY(!posix::pthread_mutex_unlock(m));
            }
            ~pthread_mutex_scoped_unlock() BOOST_NOEXCEPT
            {
                BOOST_VERIFY(!posix::pthread_mutex_lock(m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            }

        };
    }
}

#include <boost/config/abi_suffix.hpp>

#endif
