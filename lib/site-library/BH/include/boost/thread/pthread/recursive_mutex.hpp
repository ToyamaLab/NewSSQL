#ifndef BOOST_THREAD_PTHREAD_RECURSIVE_MUTEX_HPP
#define BOOST_THREAD_PTHREAD_RECURSIVE_MUTEX_HPP
// (C) Copyright 2007-8 Anthony Williams
// (C) Copyright 2011-2012 Vicente J. Botet Escriba
// Distributed under the Boost Software License, Version 1.0. (See
// accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#include <pthread.h>
#include <boost/throw_exception.hpp>
#include <boost/thread/exceptions.hpp>
#if defined BOOST_THREAD_PROVIDES_NESTED_LOCKS
#include <boost/thread/lock_types.hpp>
#endif
#include <boost/thread/thread_time.hpp>
#include <boost/assert.hpp>
#ifndef _WIN32
#include <unistd.h>
#endif
#include <boost/date_time/posix_time/conversion.hpp>
#include <errno.h>
<<<<<<< HEAD
#include <boost/thread/pthread/timespec.hpp>
#include <boost/thread/pthread/pthread_mutex_scoped_lock.hpp>
=======
#include <boost/thread/detail/platform_time.hpp>
#include <boost/thread/pthread/pthread_mutex_scoped_lock.hpp>
#include <boost/thread/pthread/pthread_helpers.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#ifdef BOOST_THREAD_USES_CHRONO
#include <boost/chrono/system_clocks.hpp>
#include <boost/chrono/ceil.hpp>
#endif
#include <boost/thread/detail/delete.hpp>

<<<<<<< HEAD
#if (defined _POSIX_TIMEOUTS && (_POSIX_TIMEOUTS-0)>=200112L) \
 || (defined __ANDROID__ && defined __ANDROID_API__ && __ANDROID_API__ >= 21)
#ifndef BOOST_PTHREAD_HAS_TIMEDLOCK
#define BOOST_PTHREAD_HAS_TIMEDLOCK
#endif
#endif
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#if  defined BOOST_HAS_PTHREAD_MUTEXATTR_SETTYPE \
 ||  defined __ANDROID__
#define BOOST_THREAD_HAS_PTHREAD_MUTEXATTR_SETTYPE
#endif

<<<<<<< HEAD
#if defined BOOST_THREAD_HAS_PTHREAD_MUTEXATTR_SETTYPE && defined BOOST_PTHREAD_HAS_TIMEDLOCK
=======
#if defined BOOST_THREAD_HAS_PTHREAD_MUTEXATTR_SETTYPE && defined BOOST_THREAD_USES_PTHREAD_TIMEDLOCK
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#define BOOST_USE_PTHREAD_RECURSIVE_TIMEDLOCK
#endif

#include <boost/config/abi_prefix.hpp>

namespace boost
{
    class recursive_mutex
    {
    private:
        pthread_mutex_t m;
#ifndef BOOST_THREAD_HAS_PTHREAD_MUTEXATTR_SETTYPE
        pthread_cond_t cond;
        bool is_locked;
        pthread_t owner;
        unsigned count;
#endif
    public:
        BOOST_THREAD_NO_COPYABLE(recursive_mutex)
        recursive_mutex()
        {
#ifdef BOOST_THREAD_HAS_PTHREAD_MUTEXATTR_SETTYPE
            pthread_mutexattr_t attr;

            int const init_attr_res=pthread_mutexattr_init(&attr);
            if(init_attr_res)
            {
                boost::throw_exception(thread_resource_error(init_attr_res, "boost:: recursive_mutex constructor failed in pthread_mutexattr_init"));
            }
            int const set_attr_res=pthread_mutexattr_settype(&attr,PTHREAD_MUTEX_RECURSIVE);
            if(set_attr_res)
            {
                BOOST_VERIFY(!pthread_mutexattr_destroy(&attr));
                boost::throw_exception(thread_resource_error(set_attr_res, "boost:: recursive_mutex constructor failed in pthread_mutexattr_settype"));
            }

<<<<<<< HEAD
            int const res=pthread_mutex_init(&m,&attr);
=======
            int const res=posix::pthread_mutex_init(&m,&attr);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            if(res)
            {
                BOOST_VERIFY(!pthread_mutexattr_destroy(&attr));
                boost::throw_exception(thread_resource_error(res, "boost:: recursive_mutex constructor failed in pthread_mutex_init"));
            }
            BOOST_VERIFY(!pthread_mutexattr_destroy(&attr));
#else
<<<<<<< HEAD
            int const res=pthread_mutex_init(&m,NULL);
=======
            int const res=posix::pthread_mutex_init(&m);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            if(res)
            {
                boost::throw_exception(thread_resource_error(res, "boost:: recursive_mutex constructor failed in pthread_mutex_init"));
            }
<<<<<<< HEAD
            int const res2=pthread_cond_init(&cond,NULL);
            if(res2)
            {
                BOOST_VERIFY(!pthread_mutex_destroy(&m));
=======
            int const res2=posix::pthread_cond_init(&cond);
            if(res2)
            {
                BOOST_VERIFY(!posix::pthread_mutex_destroy(&m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                boost::throw_exception(thread_resource_error(res2, "boost:: recursive_mutex constructor failed in pthread_cond_init"));
            }
            is_locked=false;
            count=0;
#endif
        }
        ~recursive_mutex()
        {
<<<<<<< HEAD
            BOOST_VERIFY(!pthread_mutex_destroy(&m));
#ifndef BOOST_THREAD_HAS_PTHREAD_MUTEXATTR_SETTYPE
            BOOST_VERIFY(!pthread_cond_destroy(&cond));
=======
            BOOST_VERIFY(!posix::pthread_mutex_destroy(&m));
#ifndef BOOST_THREAD_HAS_PTHREAD_MUTEXATTR_SETTYPE
            BOOST_VERIFY(!posix::pthread_cond_destroy(&cond));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#endif
        }

#ifdef BOOST_THREAD_HAS_PTHREAD_MUTEXATTR_SETTYPE
        void lock()
        {
<<<<<<< HEAD
            BOOST_VERIFY(!pthread_mutex_lock(&m));
=======
            BOOST_VERIFY(!posix::pthread_mutex_lock(&m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        void unlock()
        {
<<<<<<< HEAD
            BOOST_VERIFY(!pthread_mutex_unlock(&m));
=======
            BOOST_VERIFY(!posix::pthread_mutex_unlock(&m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        bool try_lock() BOOST_NOEXCEPT
        {
<<<<<<< HEAD
            int const res=pthread_mutex_trylock(&m);
=======
            int const res=posix::pthread_mutex_trylock(&m);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            BOOST_ASSERT(!res || res==EBUSY);
            return !res;
        }
#define BOOST_THREAD_DEFINES_RECURSIVE_MUTEX_NATIVE_HANDLE
        typedef pthread_mutex_t* native_handle_type;
        native_handle_type native_handle()
        {
            return &m;
        }

#else
        void lock()
        {
            boost::pthread::pthread_mutex_scoped_lock const local_lock(&m);
            if(is_locked && pthread_equal(owner,pthread_self()))
            {
                ++count;
                return;
            }

            while(is_locked)
            {
<<<<<<< HEAD
                BOOST_VERIFY(!pthread_cond_wait(&cond,&m));
=======
                BOOST_VERIFY(!posix::pthread_cond_wait(&cond,&m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            }
            is_locked=true;
            ++count;
            owner=pthread_self();
        }

        void unlock()
        {
            boost::pthread::pthread_mutex_scoped_lock const local_lock(&m);
            if(!--count)
            {
                is_locked=false;
            }
<<<<<<< HEAD
            BOOST_VERIFY(!pthread_cond_signal(&cond));
=======
            BOOST_VERIFY(!posix::pthread_cond_signal(&cond));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        bool try_lock()
        {
            boost::pthread::pthread_mutex_scoped_lock const local_lock(&m);
            if(is_locked && !pthread_equal(owner,pthread_self()))
            {
                return false;
            }
            is_locked=true;
            ++count;
            owner=pthread_self();
            return true;
        }

#endif

#if defined BOOST_THREAD_PROVIDES_NESTED_LOCKS
        typedef unique_lock<recursive_mutex> scoped_lock;
        typedef detail::try_lock_wrapper<recursive_mutex> scoped_try_lock;
#endif
    };

    typedef recursive_mutex recursive_try_mutex;

    class recursive_timed_mutex
    {
    private:
        pthread_mutex_t m;
#ifndef BOOST_USE_PTHREAD_RECURSIVE_TIMEDLOCK
        pthread_cond_t cond;
        bool is_locked;
        pthread_t owner;
        unsigned count;
#endif
    public:
        BOOST_THREAD_NO_COPYABLE(recursive_timed_mutex)
        recursive_timed_mutex()
        {
#ifdef BOOST_USE_PTHREAD_RECURSIVE_TIMEDLOCK
            pthread_mutexattr_t attr;

            int const init_attr_res=pthread_mutexattr_init(&attr);
            if(init_attr_res)
            {
                boost::throw_exception(thread_resource_error(init_attr_res, "boost:: recursive_timed_mutex constructor failed in pthread_mutexattr_init"));
            }
            int const set_attr_res=pthread_mutexattr_settype(&attr,PTHREAD_MUTEX_RECURSIVE);
            if(set_attr_res)
            {
                boost::throw_exception(thread_resource_error(set_attr_res, "boost:: recursive_timed_mutex constructor failed in pthread_mutexattr_settype"));
            }

<<<<<<< HEAD
            int const res=pthread_mutex_init(&m,&attr);
=======
            int const res=posix::pthread_mutex_init(&m,&attr);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            if(res)
            {
                BOOST_VERIFY(!pthread_mutexattr_destroy(&attr));
                boost::throw_exception(thread_resource_error(res, "boost:: recursive_timed_mutex constructor failed in pthread_mutex_init"));
            }
            BOOST_VERIFY(!pthread_mutexattr_destroy(&attr));
#else
<<<<<<< HEAD
            int const res=pthread_mutex_init(&m,NULL);
=======
            int const res=posix::pthread_mutex_init(&m);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            if(res)
            {
                boost::throw_exception(thread_resource_error(res, "boost:: recursive_timed_mutex constructor failed in pthread_mutex_init"));
            }
<<<<<<< HEAD
            int const res2=pthread_cond_init(&cond,NULL);
            if(res2)
            {
                BOOST_VERIFY(!pthread_mutex_destroy(&m));
=======
            int const res2=posix::pthread_cond_init(&cond);
            if(res2)
            {
                BOOST_VERIFY(!posix::pthread_mutex_destroy(&m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                boost::throw_exception(thread_resource_error(res2, "boost:: recursive_timed_mutex constructor failed in pthread_cond_init"));
            }
            is_locked=false;
            count=0;
#endif
        }
        ~recursive_timed_mutex()
        {
<<<<<<< HEAD
            BOOST_VERIFY(!pthread_mutex_destroy(&m));
#ifndef BOOST_USE_PTHREAD_RECURSIVE_TIMEDLOCK
            BOOST_VERIFY(!pthread_cond_destroy(&cond));
=======
            BOOST_VERIFY(!posix::pthread_mutex_destroy(&m));
#ifndef BOOST_USE_PTHREAD_RECURSIVE_TIMEDLOCK
            BOOST_VERIFY(!posix::pthread_cond_destroy(&cond));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#endif
        }

#if defined BOOST_THREAD_USES_DATETIME
        template<typename TimeDuration>
        bool timed_lock(TimeDuration const & relative_time)
        {
<<<<<<< HEAD
            return timed_lock(get_system_time()+relative_time);
=======
            if (relative_time.is_pos_infinity())
            {
                lock();
                return true;
            }
            if (relative_time.is_special())
            {
                return true;
            }
            detail::platform_duration d(relative_time);
#if defined(BOOST_THREAD_HAS_MONO_CLOCK) && !defined(BOOST_THREAD_INTERNAL_CLOCK_IS_MONO)
            const detail::mono_platform_timepoint ts(detail::mono_platform_clock::now() + d);
            d = (std::min)(d, detail::platform_milliseconds(BOOST_THREAD_POLL_INTERVAL_MILLISECONDS));
            while ( ! do_try_lock_until(detail::internal_platform_clock::now() + d) )
            {
              d = ts - detail::mono_platform_clock::now();
              if ( d <= detail::platform_duration::zero() ) return false; // timeout occurred
              d = (std::min)(d, detail::platform_milliseconds(BOOST_THREAD_POLL_INTERVAL_MILLISECONDS));
            }
            return true;
#else
            return do_try_lock_until(detail::internal_platform_clock::now() + d);
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }
#endif

#ifdef BOOST_USE_PTHREAD_RECURSIVE_TIMEDLOCK
        void lock()
        {
<<<<<<< HEAD
            BOOST_VERIFY(!pthread_mutex_lock(&m));
=======
            BOOST_VERIFY(!posix::pthread_mutex_lock(&m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        void unlock()
        {
<<<<<<< HEAD
            BOOST_VERIFY(!pthread_mutex_unlock(&m));
=======
            BOOST_VERIFY(!posix::pthread_mutex_unlock(&m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        bool try_lock()
        {
<<<<<<< HEAD
            int const res=pthread_mutex_trylock(&m);
=======
            int const res=posix::pthread_mutex_trylock(&m);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            BOOST_ASSERT(!res || res==EBUSY);
            return !res;
        }
    private:
<<<<<<< HEAD
        bool do_try_lock_until(struct timespec const &timeout)
        {
            int const res=pthread_mutex_timedlock(&m,&timeout);
=======
        bool do_try_lock_until(detail::internal_platform_timepoint const &timeout)
        {
            int const res=pthread_mutex_timedlock(&m,&timeout.getTs());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            BOOST_ASSERT(!res || res==ETIMEDOUT);
            return !res;
        }

    public:

#else
        void lock()
        {
            boost::pthread::pthread_mutex_scoped_lock const local_lock(&m);
            if(is_locked && pthread_equal(owner,pthread_self()))
            {
                ++count;
                return;
            }

            while(is_locked)
            {
<<<<<<< HEAD
                BOOST_VERIFY(!pthread_cond_wait(&cond,&m));
=======
                BOOST_VERIFY(!posix::pthread_cond_wait(&cond,&m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            }
            is_locked=true;
            ++count;
            owner=pthread_self();
        }

        void unlock()
        {
            boost::pthread::pthread_mutex_scoped_lock const local_lock(&m);
            if(!--count)
            {
                is_locked=false;
            }
<<<<<<< HEAD
            BOOST_VERIFY(!pthread_cond_signal(&cond));
=======
            BOOST_VERIFY(!posix::pthread_cond_signal(&cond));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        bool try_lock() BOOST_NOEXCEPT
        {
            boost::pthread::pthread_mutex_scoped_lock const local_lock(&m);
            if(is_locked && !pthread_equal(owner,pthread_self()))
            {
                return false;
            }
            is_locked=true;
            ++count;
            owner=pthread_self();
            return true;
        }

    private:
<<<<<<< HEAD
        bool do_try_lock_until(struct timespec const &timeout)
=======
        bool do_try_lock_until(detail::internal_platform_timepoint const &timeout)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            boost::pthread::pthread_mutex_scoped_lock const local_lock(&m);
            if(is_locked && pthread_equal(owner,pthread_self()))
            {
                ++count;
                return true;
            }
            while(is_locked)
            {
<<<<<<< HEAD
                int const cond_res=pthread_cond_timedwait(&cond,&m,&timeout);
                if(cond_res==ETIMEDOUT)
                {
                    return false;
                }
                BOOST_ASSERT(!cond_res);
            }
=======
                int const cond_res=posix::pthread_cond_timedwait(&cond,&m,&timeout.getTs());
                if(cond_res==ETIMEDOUT)
                {
                    break;
                }
                BOOST_ASSERT(!cond_res);
            }
            if(is_locked)
            {
                return false;
            }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            is_locked=true;
            ++count;
            owner=pthread_self();
            return true;
        }
    public:

#endif

#if defined BOOST_THREAD_USES_DATETIME
        bool timed_lock(system_time const & abs_time)
        {
<<<<<<< HEAD
            struct timespec const ts=detail::to_timespec(abs_time);
            return do_try_lock_until(ts);
=======
            const detail::real_platform_timepoint ts(abs_time);
#if defined BOOST_THREAD_INTERNAL_CLOCK_IS_MONO
            detail::platform_duration d(ts - detail::real_platform_clock::now());
            d = (std::min)(d, detail::platform_milliseconds(BOOST_THREAD_POLL_INTERVAL_MILLISECONDS));
            while ( ! do_try_lock_until(detail::internal_platform_clock::now() + d) )
            {
              d = ts - detail::real_platform_clock::now();
              if ( d <= detail::platform_duration::zero() ) return false; // timeout occurred
              d = (std::min)(d, detail::platform_milliseconds(BOOST_THREAD_POLL_INTERVAL_MILLISECONDS));
            }
            return true;
#else
            return do_try_lock_until(ts);
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }
#endif
#ifdef BOOST_THREAD_USES_CHRONO
        template <class Rep, class Period>
        bool try_lock_for(const chrono::duration<Rep, Period>& rel_time)
        {
          return try_lock_until(chrono::steady_clock::now() + rel_time);
        }
        template <class Clock, class Duration>
        bool try_lock_until(const chrono::time_point<Clock, Duration>& t)
        {
<<<<<<< HEAD
          using namespace chrono;
          system_clock::time_point     s_now = system_clock::now();
          typename Clock::time_point  c_now = Clock::now();
          return try_lock_until(s_now + ceil<nanoseconds>(t - c_now));
        }
        template <class Duration>
        bool try_lock_until(const chrono::time_point<chrono::system_clock, Duration>& t)
        {
          using namespace chrono;
          typedef time_point<system_clock, nanoseconds> nano_sys_tmpt;
          return try_lock_until(nano_sys_tmpt(ceil<nanoseconds>(t.time_since_epoch())));
        }
        bool try_lock_until(const chrono::time_point<chrono::system_clock, chrono::nanoseconds>& tp)
        {
          //using namespace chrono;
          chrono::nanoseconds d = tp.time_since_epoch();
          timespec ts = boost::detail::to_timespec(d);
=======
          typedef typename common_type<Duration, typename Clock::duration>::type common_duration;
          common_duration d(t - Clock::now());
          d = (std::min)(d, common_duration(chrono::milliseconds(BOOST_THREAD_POLL_INTERVAL_MILLISECONDS)));
          while ( ! try_lock_until(detail::internal_chrono_clock::now() + d))
          {
              d = t - Clock::now();
              if ( d <= common_duration::zero() ) return false; // timeout occurred
              d = (std::min)(d, common_duration(chrono::milliseconds(BOOST_THREAD_POLL_INTERVAL_MILLISECONDS)));
          }
          return true;

        }
        template <class Duration>
        bool try_lock_until(const chrono::time_point<detail::internal_chrono_clock, Duration>& t)
        {
          detail::internal_platform_timepoint ts(t);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
          return do_try_lock_until(ts);
        }
#endif

#define BOOST_THREAD_DEFINES_RECURSIVE_TIMED_MUTEX_NATIVE_HANDLE
        typedef pthread_mutex_t* native_handle_type;
        native_handle_type native_handle()
        {
            return &m;
        }

#if defined BOOST_THREAD_PROVIDES_NESTED_LOCKS
        typedef unique_lock<recursive_timed_mutex> scoped_timed_lock;
        typedef detail::try_lock_wrapper<recursive_timed_mutex> scoped_try_lock;
        typedef scoped_timed_lock scoped_lock;
#endif
    };

}

#include <boost/config/abi_suffix.hpp>

#endif
