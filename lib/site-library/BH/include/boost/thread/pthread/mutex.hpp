#ifndef BOOST_THREAD_PTHREAD_MUTEX_HPP
#define BOOST_THREAD_PTHREAD_MUTEX_HPP
// (C) Copyright 2007-8 Anthony Williams
// (C) Copyright 2011,2012,2015 Vicente J. Botet Escriba
// Distributed under the Boost Software License, Version 1.0. (See
// accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#include <boost/thread/detail/config.hpp>
#include <boost/assert.hpp>
#include <pthread.h>
#include <boost/throw_exception.hpp>
#include <boost/core/ignore_unused.hpp>
#include <boost/thread/exceptions.hpp>
#if defined BOOST_THREAD_PROVIDES_NESTED_LOCKS
#include <boost/thread/lock_types.hpp>
#endif
#include <boost/thread/thread_time.hpp>
<<<<<<< HEAD
#include <boost/thread/xtime.hpp>
#include <boost/assert.hpp>
#include <errno.h>
#include <boost/thread/pthread/timespec.hpp>
#include <boost/thread/pthread/pthread_mutex_scoped_lock.hpp>
=======
#if defined BOOST_THREAD_USES_DATETIME
#include <boost/thread/xtime.hpp>
#endif
#include <boost/assert.hpp>
#include <errno.h>
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
#if (defined(_POSIX_TIMEOUTS) && (_POSIX_TIMEOUTS-0)>=200112L) \
 || (defined(__ANDROID__) && defined(__ANDROID_API__) && __ANDROID_API__ >= 21)
#ifndef BOOST_PTHREAD_HAS_TIMEDLOCK
#define BOOST_PTHREAD_HAS_TIMEDLOCK
#endif
#endif


#include <boost/config/abi_prefix.hpp>

#ifndef BOOST_THREAD_HAS_NO_EINTR_BUG
#define BOOST_THREAD_HAS_EINTR_BUG
#endif

namespace boost
{
  namespace posix {
#ifdef BOOST_THREAD_HAS_EINTR_BUG
    BOOST_FORCEINLINE int pthread_mutex_destroy(pthread_mutex_t* m)
    {
      int ret;
      do
      {
          ret = ::pthread_mutex_destroy(m);
      } while (ret == EINTR);
      return ret;
    }
    BOOST_FORCEINLINE int pthread_mutex_lock(pthread_mutex_t* m)
    {
      int ret;
      do
      {
          ret = ::pthread_mutex_lock(m);
      } while (ret == EINTR);
      return ret;
    }
    BOOST_FORCEINLINE int pthread_mutex_unlock(pthread_mutex_t* m)
    {
      int ret;
      do
      {
          ret = ::pthread_mutex_unlock(m);
      } while (ret == EINTR);
      return ret;
    }
#else
    BOOST_FORCEINLINE int pthread_mutex_destroy(pthread_mutex_t* m)
    {
      return ::pthread_mutex_destroy(m);
    }
    BOOST_FORCEINLINE int pthread_mutex_lock(pthread_mutex_t* m)
    {
      return ::pthread_mutex_lock(m);
    }
    BOOST_FORCEINLINE int pthread_mutex_unlock(pthread_mutex_t* m)
    {
      return ::pthread_mutex_unlock(m);
    }

#endif

  }
    class mutex
=======

#include <boost/config/abi_prefix.hpp>

namespace boost
{

    class BOOST_THREAD_CAPABILITY("mutex") mutex
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
    private:
        pthread_mutex_t m;
    public:
        BOOST_THREAD_NO_COPYABLE(mutex)

        mutex()
        {
<<<<<<< HEAD
            int const res=pthread_mutex_init(&m,NULL);
=======
            int const res=posix::pthread_mutex_init(&m);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            if(res)
            {
                boost::throw_exception(thread_resource_error(res, "boost:: mutex constructor failed in pthread_mutex_init"));
            }
        }
        ~mutex()
        {
<<<<<<< HEAD
          int const res = posix::pthread_mutex_destroy(&m);
          boost::ignore_unused(res);
          BOOST_ASSERT(!res);
        }

        void lock()
=======
          BOOST_VERIFY(!posix::pthread_mutex_destroy(&m));
        }

        void lock() BOOST_THREAD_ACQUIRE()
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            int res = posix::pthread_mutex_lock(&m);
            if (res)
            {
                boost::throw_exception(lock_error(res,"boost: mutex lock failed in pthread_mutex_lock"));
            }
        }

<<<<<<< HEAD
        void unlock()
        {
            int res = posix::pthread_mutex_unlock(&m);
            (void)res;
            BOOST_ASSERT(res == 0);
//            if (res)
//            {
//                boost::throw_exception(lock_error(res,"boost: mutex unlock failed in pthread_mutex_unlock"));
//            }
        }

        bool try_lock()
        {
            int res;
            do
            {
                res = pthread_mutex_trylock(&m);
            } while (res == EINTR);
=======
        void unlock() BOOST_THREAD_RELEASE()
        {
            BOOST_VERIFY(!posix::pthread_mutex_unlock(&m));
        }

        bool try_lock() BOOST_THREAD_TRY_ACQUIRE(true)
        {
            int res = posix::pthread_mutex_trylock(&m);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            if (res==EBUSY)
            {
                return false;
            }

            return !res;
        }

#define BOOST_THREAD_DEFINES_MUTEX_NATIVE_HANDLE
        typedef pthread_mutex_t* native_handle_type;
        native_handle_type native_handle()
        {
            return &m;
        }

#if defined BOOST_THREAD_PROVIDES_NESTED_LOCKS
        typedef unique_lock<mutex> scoped_lock;
        typedef detail::try_lock_wrapper<mutex> scoped_try_lock;
#endif
    };

    typedef mutex try_mutex;

    class timed_mutex
    {
    private:
        pthread_mutex_t m;
<<<<<<< HEAD
#ifndef BOOST_PTHREAD_HAS_TIMEDLOCK
=======
#ifndef BOOST_THREAD_USES_PTHREAD_TIMEDLOCK
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        pthread_cond_t cond;
        bool is_locked;
#endif
    public:
        BOOST_THREAD_NO_COPYABLE(timed_mutex)
        timed_mutex()
        {
<<<<<<< HEAD
            int const res=pthread_mutex_init(&m,NULL);
=======
            int const res=posix::pthread_mutex_init(&m);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            if(res)
            {
                boost::throw_exception(thread_resource_error(res, "boost:: timed_mutex constructor failed in pthread_mutex_init"));
            }
<<<<<<< HEAD
#ifndef BOOST_PTHREAD_HAS_TIMEDLOCK
            int const res2=pthread_cond_init(&cond,NULL);
            if(res2)
            {
                BOOST_VERIFY(!posix::pthread_mutex_destroy(&m));
                //BOOST_VERIFY(!pthread_mutex_destroy(&m));
=======
#ifndef BOOST_THREAD_USES_PTHREAD_TIMEDLOCK
            int const res2=posix::pthread_cond_init(&cond);
            if(res2)
            {
                BOOST_VERIFY(!posix::pthread_mutex_destroy(&m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                boost::throw_exception(thread_resource_error(res2, "boost:: timed_mutex constructor failed in pthread_cond_init"));
            }
            is_locked=false;
#endif
        }
        ~timed_mutex()
        {
            BOOST_VERIFY(!posix::pthread_mutex_destroy(&m));
<<<<<<< HEAD
#ifndef BOOST_PTHREAD_HAS_TIMEDLOCK
            BOOST_VERIFY(!pthread_cond_destroy(&cond));
=======
#ifndef BOOST_THREAD_USES_PTHREAD_TIMEDLOCK
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
        bool timed_lock(boost::xtime const & absolute_time)
        {
            return timed_lock(system_time(absolute_time));
        }
#endif
<<<<<<< HEAD
#ifdef BOOST_PTHREAD_HAS_TIMEDLOCK
=======
#ifdef BOOST_THREAD_USES_PTHREAD_TIMEDLOCK
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        void lock()
        {
            int res = posix::pthread_mutex_lock(&m);
            if (res)
            {
                boost::throw_exception(lock_error(res,"boost: mutex lock failed in pthread_mutex_lock"));
            }
        }

        void unlock()
        {
<<<<<<< HEAD
            int res = posix::pthread_mutex_unlock(&m);
            (void)res;
            BOOST_ASSERT(res == 0);
//            if (res)
//            {
//                boost::throw_exception(lock_error(res,"boost: mutex unlock failed in pthread_mutex_unlock"));
//            }
=======
            BOOST_VERIFY(!posix::pthread_mutex_unlock(&m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        bool try_lock()
        {
<<<<<<< HEAD
          int res;
          do
          {
              res = pthread_mutex_trylock(&m);
          } while (res == EINTR);
=======
          int res = posix::pthread_mutex_trylock(&m);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
          if (res==EBUSY)
          {
              return false;
          }

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
            while(is_locked)
            {
<<<<<<< HEAD
                BOOST_VERIFY(!pthread_cond_wait(&cond,&m));
=======
                BOOST_VERIFY(!posix::pthread_cond_wait(&cond,&m));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            }
            is_locked=true;
        }

        void unlock()
        {
            boost::pthread::pthread_mutex_scoped_lock const local_lock(&m);
            is_locked=false;
<<<<<<< HEAD
            BOOST_VERIFY(!pthread_cond_signal(&cond));
=======
            BOOST_VERIFY(!posix::pthread_cond_signal(&cond));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        bool try_lock()
        {
            boost::pthread::pthread_mutex_scoped_lock const local_lock(&m);
            if(is_locked)
            {
                return false;
            }
            is_locked=true;
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
            return true;
        }
    public:
#endif

#if defined BOOST_THREAD_USES_DATETIME
        bool timed_lock(system_time const & abs_time)
        {
<<<<<<< HEAD
            struct timespec const ts=boost::detail::to_timespec(abs_time);
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

#define BOOST_THREAD_DEFINES_TIMED_MUTEX_NATIVE_HANDLE
        typedef pthread_mutex_t* native_handle_type;
        native_handle_type native_handle()
        {
            return &m;
        }

#if defined BOOST_THREAD_PROVIDES_NESTED_LOCKS
        typedef unique_lock<timed_mutex> scoped_timed_lock;
        typedef detail::try_lock_wrapper<timed_mutex> scoped_try_lock;
        typedef scoped_timed_lock scoped_lock;
#endif
    };
<<<<<<< HEAD

=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

#include <boost/config/abi_suffix.hpp>


#endif
