#ifndef BOOST_THREAD_CONDITION_VARIABLE_PTHREAD_HPP
#define BOOST_THREAD_CONDITION_VARIABLE_PTHREAD_HPP
// Distributed under the Boost Software License, Version 1.0. (See
// accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)
// (C) Copyright 2007-10 Anthony Williams
// (C) Copyright 2011-2012 Vicente J. Botet Escriba

<<<<<<< HEAD
#include <boost/thread/pthread/timespec.hpp>
#include <boost/thread/pthread/pthread_mutex_scoped_lock.hpp>
#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
=======
#include <boost/thread/detail/platform_time.hpp>
#include <boost/thread/pthread/pthread_mutex_scoped_lock.hpp>
#include <boost/thread/pthread/pthread_helpers.hpp>

#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
#include <boost/thread/interruption.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/thread/pthread/thread_data.hpp>
#endif
#include <boost/thread/pthread/condition_variable_fwd.hpp>
#ifdef BOOST_THREAD_USES_CHRONO
#include <boost/chrono/system_clocks.hpp>
#include <boost/chrono/ceil.hpp>
#endif
#include <boost/thread/detail/delete.hpp>

<<<<<<< HEAD
=======
#include <algorithm>

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/config/abi_prefix.hpp>

namespace boost
{
<<<<<<< HEAD
#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
    namespace this_thread
    {
        void BOOST_THREAD_DECL interruption_point();
    }
#endif

=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    namespace thread_cv_detail
    {
        template<typename MutexType>
        struct lock_on_exit
        {
            MutexType* m;

            lock_on_exit():
                m(0)
            {}

            void activate(MutexType& m_)
            {
                m_.unlock();
                m=&m_;
            }
            void deactivate()
            {
                if (m)
                {
                    m->lock();
                }
                m = 0;
            }
            ~lock_on_exit() BOOST_NOEXCEPT_IF(false)
            {
                if (m)
                {
                    m->lock();
                }
           }
        };
    }

    inline void condition_variable::wait(unique_lock<mutex>& m)
    {
#if defined BOOST_THREAD_THROW_IF_PRECONDITION_NOT_SATISFIED
        if(! m.owns_lock())
        {
            boost::throw_exception(condition_error(-1, "boost::condition_variable::wait() failed precondition mutex not owned"));
        }
#endif
        int res=0;
        {
#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
            thread_cv_detail::lock_on_exit<unique_lock<mutex> > guard;
            detail::interruption_checker check_for_interruption(&internal_mutex,&cond);
            pthread_mutex_t* the_mutex = &internal_mutex;
            guard.activate(m);
<<<<<<< HEAD
            res = pthread_cond_wait(&cond,the_mutex);
=======
            res = posix::pthread_cond_wait(&cond,the_mutex);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            check_for_interruption.unlock_if_locked();
            guard.deactivate();
#else
            pthread_mutex_t* the_mutex = m.mutex()->native_handle();
<<<<<<< HEAD
            res = pthread_cond_wait(&cond,the_mutex);
=======
            res = posix::pthread_cond_wait(&cond,the_mutex);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#endif
        }
#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
        this_thread::interruption_point();
#endif
<<<<<<< HEAD
        if(res && res != EINTR)
=======
        if(res)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            boost::throw_exception(condition_error(res, "boost::condition_variable::wait failed in pthread_cond_wait"));
        }
    }

<<<<<<< HEAD
    inline bool condition_variable::do_wait_until(
                unique_lock<mutex>& m,
                struct timespec const &timeout)
=======
    // When this function returns true:
    // * A notification (or sometimes a spurious OS signal) has been received
    // * Do not assume that the timeout has not been reached
    // * Do not assume that the predicate has been changed
    //
    // When this function returns false:
    // * The timeout has been reached
    // * Do not assume that a notification has not been received
    // * Do not assume that the predicate has not been changed
    inline bool condition_variable::do_wait_until(
                unique_lock<mutex>& m,
                detail::internal_platform_timepoint const &timeout)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
#if defined BOOST_THREAD_THROW_IF_PRECONDITION_NOT_SATISFIED
        if (!m.owns_lock())
        {
            boost::throw_exception(condition_error(EPERM, "boost::condition_variable::do_wait_until() failed precondition mutex not owned"));
        }
#endif
        int cond_res;
        {
#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
            thread_cv_detail::lock_on_exit<unique_lock<mutex> > guard;
            detail::interruption_checker check_for_interruption(&internal_mutex,&cond);
            pthread_mutex_t* the_mutex = &internal_mutex;
            guard.activate(m);
<<<<<<< HEAD
            cond_res=pthread_cond_timedwait(&cond,the_mutex,&timeout);
=======
            cond_res=posix::pthread_cond_timedwait(&cond,the_mutex,&timeout.getTs());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            check_for_interruption.unlock_if_locked();
            guard.deactivate();
#else
            pthread_mutex_t* the_mutex = m.mutex()->native_handle();
<<<<<<< HEAD
            cond_res=pthread_cond_timedwait(&cond,the_mutex,&timeout);
=======
            cond_res=posix::pthread_cond_timedwait(&cond,the_mutex,&timeout.getTs());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#endif
        }
#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
        this_thread::interruption_point();
#endif
        if(cond_res==ETIMEDOUT)
        {
            return false;
        }
        if(cond_res)
        {
            boost::throw_exception(condition_error(cond_res, "boost::condition_variable::do_wait_until failed in pthread_cond_timedwait"));
        }
        return true;
    }

    inline void condition_variable::notify_one() BOOST_NOEXCEPT
    {
#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
        boost::pthread::pthread_mutex_scoped_lock internal_lock(&internal_mutex);
#endif
<<<<<<< HEAD
        BOOST_VERIFY(!pthread_cond_signal(&cond));
=======
        BOOST_VERIFY(!posix::pthread_cond_signal(&cond));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }

    inline void condition_variable::notify_all() BOOST_NOEXCEPT
    {
#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
        boost::pthread::pthread_mutex_scoped_lock internal_lock(&internal_mutex);
#endif
<<<<<<< HEAD
        BOOST_VERIFY(!pthread_cond_broadcast(&cond));
=======
        BOOST_VERIFY(!posix::pthread_cond_broadcast(&cond));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }

    class condition_variable_any
    {
        pthread_mutex_t internal_mutex;
        pthread_cond_t cond;

    public:
        BOOST_THREAD_NO_COPYABLE(condition_variable_any)
        condition_variable_any()
        {
<<<<<<< HEAD
            int const res=pthread_mutex_init(&internal_mutex,NULL);
=======
            int const res=posix::pthread_mutex_init(&internal_mutex);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            if(res)
            {
                boost::throw_exception(thread_resource_error(res, "boost::condition_variable_any::condition_variable_any() failed in pthread_mutex_init"));
            }
<<<<<<< HEAD
            int const res2 = detail::monotonic_pthread_cond_init(cond);
            if(res2)
            {
                BOOST_VERIFY(!pthread_mutex_destroy(&internal_mutex));
                boost::throw_exception(thread_resource_error(res2, "boost::condition_variable_any::condition_variable_any() failed in detail::monotonic_pthread_cond_init"));
=======
            int const res2 = posix::pthread_cond_init(&cond);
            if(res2)
            {
                BOOST_VERIFY(!posix::pthread_mutex_destroy(&internal_mutex));
                boost::throw_exception(thread_resource_error(res2, "boost::condition_variable_any::condition_variable_any() failed in pthread_cond_init"));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            }
        }
        ~condition_variable_any()
        {
<<<<<<< HEAD
            BOOST_VERIFY(!pthread_mutex_destroy(&internal_mutex));
            BOOST_VERIFY(!pthread_cond_destroy(&cond));
=======
            BOOST_VERIFY(!posix::pthread_mutex_destroy(&internal_mutex));
            BOOST_VERIFY(!posix::pthread_cond_destroy(&cond));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        template<typename lock_type>
        void wait(lock_type& m)
        {
            int res=0;
            {
                thread_cv_detail::lock_on_exit<lock_type> guard;
#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
                detail::interruption_checker check_for_interruption(&internal_mutex,&cond);
#else
                boost::pthread::pthread_mutex_scoped_lock check_for_interruption(&internal_mutex);
#endif
                guard.activate(m);
<<<<<<< HEAD
                res=pthread_cond_wait(&cond,&internal_mutex);
=======
                res=posix::pthread_cond_wait(&cond,&internal_mutex);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                check_for_interruption.unlock_if_locked();
                guard.deactivate();
            }
#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
            this_thread::interruption_point();
#endif
            if(res)
            {
                boost::throw_exception(condition_error(res, "boost::condition_variable_any::wait() failed in pthread_cond_wait"));
            }
        }

        template<typename lock_type,typename predicate_type>
        void wait(lock_type& m,predicate_type pred)
        {
<<<<<<< HEAD
            while(!pred()) wait(m);
=======
            while (!pred())
            {
                wait(m);
            }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

#if defined BOOST_THREAD_USES_DATETIME
        template<typename lock_type>
        bool timed_wait(lock_type& m,boost::system_time const& abs_time)
        {
<<<<<<< HEAD
            struct timespec const timeout=detail::to_timespec(abs_time);
            return do_wait_until(m, timeout);
        }
        template<typename lock_type>
        bool timed_wait(lock_type& m,xtime const& abs_time)
=======
#if defined BOOST_THREAD_WAIT_BUG
            const detail::real_platform_timepoint ts(abs_time + BOOST_THREAD_WAIT_BUG);
#else
            const detail::real_platform_timepoint ts(abs_time);
#endif
#if defined BOOST_THREAD_INTERNAL_CLOCK_IS_MONO
            // The system time may jump while this function is waiting. To compensate for this and time
            // out near the correct time, we could call do_wait_until() in a loop with a short timeout
            // and recheck the time remaining each time through the loop. However, because we can't
            // check the predicate each time do_wait_until() completes, this introduces the possibility
            // of not exiting the function when a notification occurs, since do_wait_until() may report
            // that it timed out even though a notification was received. The best this function can do
            // is report correctly whether or not it reached the timeout time.
            const detail::platform_duration d(ts - detail::real_platform_clock::now());
            do_wait_until(m, detail::internal_platform_clock::now() + d);
            return ts > detail::real_platform_clock::now();
#else
            return do_wait_until(m, ts);
#endif
        }
        template<typename lock_type>
        bool timed_wait(lock_type& m,::boost::xtime const& abs_time)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            return timed_wait(m,system_time(abs_time));
        }

        template<typename lock_type,typename duration_type>
        bool timed_wait(lock_type& m,duration_type const& wait_duration)
        {
<<<<<<< HEAD
            return timed_wait(m,get_system_time()+wait_duration);
=======
            if (wait_duration.is_pos_infinity())
            {
                wait(m);
                return true;
            }
            if (wait_duration.is_special())
            {
                return true;
            }
            detail::platform_duration d(wait_duration);
#if defined(BOOST_THREAD_HAS_MONO_CLOCK) && !defined(BOOST_THREAD_INTERNAL_CLOCK_IS_MONO)
            // The system time may jump while this function is waiting. To compensate for this and time
            // out near the correct time, we could call do_wait_until() in a loop with a short timeout
            // and recheck the time remaining each time through the loop. However, because we can't
            // check the predicate each time do_wait_until() completes, this introduces the possibility
            // of not exiting the function when a notification occurs, since do_wait_until() may report
            // that it timed out even though a notification was received. The best this function can do
            // is report correctly whether or not it reached the timeout time.
            const detail::mono_platform_timepoint ts(detail::mono_platform_clock::now() + d);
            do_wait_until(m, detail::internal_platform_clock::now() + d);
            return ts > detail::mono_platform_clock::now();
#else
            return do_wait_until(m, detail::internal_platform_clock::now() + d);
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        template<typename lock_type,typename predicate_type>
        bool timed_wait(lock_type& m,boost::system_time const& abs_time, predicate_type pred)
        {
<<<<<<< HEAD
            while (!pred())
            {
                if(!timed_wait(m, abs_time))
                    return pred();
            }
            return true;
        }

        template<typename lock_type,typename predicate_type>
        bool timed_wait(lock_type& m,xtime const& abs_time, predicate_type pred)
=======
#if defined BOOST_THREAD_WAIT_BUG
            const detail::real_platform_timepoint ts(abs_time + BOOST_THREAD_WAIT_BUG);
#else
            const detail::real_platform_timepoint ts(abs_time);
#endif
            while (!pred())
            {
#if defined BOOST_THREAD_INTERNAL_CLOCK_IS_MONO
                // The system time may jump while this function is waiting. To compensate for this
                // and time out near the correct time, we call do_wait_until() in a loop with a
                // short timeout and recheck the time remaining each time through the loop.
                detail::platform_duration d(ts - detail::real_platform_clock::now());
                if (d <= detail::platform_duration::zero()) break; // timeout occurred
                d = (std::min)(d, detail::platform_milliseconds(BOOST_THREAD_POLL_INTERVAL_MILLISECONDS));
                do_wait_until(m, detail::internal_platform_clock::now() + d);
#else
                if (!do_wait_until(m, ts)) break; // timeout occurred
#endif
            }
            return pred();
        }

        template<typename lock_type,typename predicate_type>
        bool timed_wait(lock_type& m,::boost::xtime const& abs_time, predicate_type pred)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            return timed_wait(m,system_time(abs_time),pred);
        }

        template<typename lock_type,typename duration_type,typename predicate_type>
        bool timed_wait(lock_type& m,duration_type const& wait_duration,predicate_type pred)
        {
<<<<<<< HEAD
            return timed_wait(m,get_system_time()+wait_duration,pred);
        }
#endif
#ifndef BOOST_THREAD_HAS_CONDATTR_SET_CLOCK_MONOTONIC
=======
            if (wait_duration.is_pos_infinity())
            {
                while (!pred())
                {
                    wait(m);
                }
                return true;
            }
            if (wait_duration.is_special())
            {
                return pred();
            }
            detail::platform_duration d(wait_duration);
#if defined(BOOST_THREAD_HAS_MONO_CLOCK) && !defined(BOOST_THREAD_INTERNAL_CLOCK_IS_MONO)
            // The system time may jump while this function is waiting. To compensate for this
            // and time out near the correct time, we call do_wait_until() in a loop with a
            // short timeout and recheck the time remaining each time through the loop.
            const detail::mono_platform_timepoint ts(detail::mono_platform_clock::now() + d);
            while (!pred())
            {
                if (d <= detail::platform_duration::zero()) break; // timeout occurred
                d = (std::min)(d, detail::platform_milliseconds(BOOST_THREAD_POLL_INTERVAL_MILLISECONDS));
                do_wait_until(m, detail::internal_platform_clock::now() + d);
                d = ts - detail::mono_platform_clock::now();
            }
#else
            const detail::internal_platform_timepoint ts(detail::internal_platform_clock::now() + d);
            while (!pred())
            {
                if (!do_wait_until(m, ts)) break; // timeout occurred
            }
#endif
            return pred();
        }
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#ifdef BOOST_THREAD_USES_CHRONO
        template <class lock_type,class Duration>
        cv_status
        wait_until(
                lock_type& lock,
<<<<<<< HEAD
                const chrono::time_point<chrono::system_clock, Duration>& t)
        {
          using namespace chrono;
          typedef time_point<system_clock, nanoseconds> nano_sys_tmpt;
          wait_until(lock,
                        nano_sys_tmpt(ceil<nanoseconds>(t.time_since_epoch())));
          return system_clock::now() < t ? cv_status::no_timeout :
                                             cv_status::timeout;
=======
                const chrono::time_point<detail::internal_chrono_clock, Duration>& t)
        {
            const boost::detail::internal_platform_timepoint ts(t);
            if (do_wait_until(lock, ts)) return cv_status::no_timeout;
            else return cv_status::timeout;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        template <class lock_type, class Clock, class Duration>
        cv_status
        wait_until(
                lock_type& lock,
                const chrono::time_point<Clock, Duration>& t)
        {
<<<<<<< HEAD
          using namespace chrono;
          system_clock::time_point     s_now = system_clock::now();
          typename Clock::time_point  c_now = Clock::now();
          wait_until(lock, s_now + ceil<nanoseconds>(t - c_now));
          return Clock::now() < t ? cv_status::no_timeout : cv_status::timeout;
=======
            // The system time may jump while this function is waiting. To compensate for this and time
            // out near the correct time, we could call do_wait_until() in a loop with a short timeout
            // and recheck the time remaining each time through the loop. However, because we can't
            // check the predicate each time do_wait_until() completes, this introduces the possibility
            // of not exiting the function when a notification occurs, since do_wait_until() may report
            // that it timed out even though a notification was received. The best this function can do
            // is report correctly whether or not it reached the timeout time.
            typedef typename common_type<Duration, typename Clock::duration>::type common_duration;
            common_duration d(t - Clock::now());
            do_wait_until(lock, detail::internal_chrono_clock::now() + d);
            if (t > Clock::now()) return cv_status::no_timeout;
            else return cv_status::timeout;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        template <class lock_type, class Rep, class Period>
        cv_status
        wait_for(
                lock_type& lock,
                const chrono::duration<Rep, Period>& d)
        {
<<<<<<< HEAD
          using namespace chrono;
          system_clock::time_point s_now = system_clock::now();
          steady_clock::time_point c_now = steady_clock::now();
          wait_until(lock, s_now + ceil<nanoseconds>(d));
          return steady_clock::now() - c_now < d ? cv_status::no_timeout :
                                                   cv_status::timeout;

        }

        template <class lock_type>
        cv_status wait_until(
            lock_type& lk,
            chrono::time_point<chrono::system_clock, chrono::nanoseconds> tp)
        {
            using namespace chrono;
            nanoseconds d = tp.time_since_epoch();
            timespec ts = boost::detail::to_timespec(d);
            if (do_wait_until(lk, ts)) return cv_status::no_timeout;
            else return cv_status::timeout;
        }
#endif
#else // defined BOOST_THREAD_HAS_CONDATTR_SET_CLOCK_MONOTONIC
#ifdef BOOST_THREAD_USES_CHRONO

        template <class lock_type, class Duration>
        cv_status
        wait_until(
            lock_type& lock,
            const chrono::time_point<chrono::steady_clock, Duration>& t)
        {
            using namespace chrono;
            typedef time_point<steady_clock, nanoseconds> nano_sys_tmpt;
            wait_until(lock,
                        nano_sys_tmpt(ceil<nanoseconds>(t.time_since_epoch())));
            return steady_clock::now() < t ? cv_status::no_timeout :
                                             cv_status::timeout;
        }

        template <class lock_type, class Clock, class Duration>
        cv_status
        wait_until(
            lock_type& lock,
            const chrono::time_point<Clock, Duration>& t)
        {
            using namespace chrono;
            steady_clock::time_point     s_now = steady_clock::now();
            typename Clock::time_point  c_now = Clock::now();
            wait_until(lock, s_now + ceil<nanoseconds>(t - c_now));
            return Clock::now() < t ? cv_status::no_timeout : cv_status::timeout;
        }

        template <class lock_type, class Rep, class Period>
        cv_status
        wait_for(
            lock_type& lock,
            const chrono::duration<Rep, Period>& d)
        {
            using namespace chrono;
            steady_clock::time_point c_now = steady_clock::now();
            wait_until(lock, c_now + ceil<nanoseconds>(d));
            return steady_clock::now() - c_now < d ? cv_status::no_timeout :
                                                   cv_status::timeout;
        }

        template <class lock_type>
        inline cv_status wait_until(
            lock_type& lock,
            chrono::time_point<chrono::steady_clock, chrono::nanoseconds> tp)
        {
            using namespace chrono;
            nanoseconds d = tp.time_since_epoch();
            timespec ts = boost::detail::to_timespec(d);
            if (do_wait_until(lock, ts)) return cv_status::no_timeout;
            else return cv_status::timeout;
        }

#endif
#endif // defined BOOST_THREAD_HAS_CONDATTR_SET_CLOCK_MONOTONIC

#ifdef BOOST_THREAD_USES_CHRONO
=======
            return wait_until(lock, chrono::steady_clock::now() + d);
        }

        template <class lock_type, class Duration, class Predicate>
        bool
        wait_until(
                lock_type& lock,
                const chrono::time_point<detail::internal_chrono_clock, Duration>& t,
                Predicate pred)
        {
            const detail::internal_platform_timepoint ts(t);
            while (!pred())
            {
                if (!do_wait_until(lock, ts)) break; // timeout occurred
            }
            return pred();
        }

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        template <class lock_type, class Clock, class Duration, class Predicate>
        bool
        wait_until(
                lock_type& lock,
                const chrono::time_point<Clock, Duration>& t,
                Predicate pred)
        {
<<<<<<< HEAD
            while (!pred())
            {
                if (wait_until(lock, t) == cv_status::timeout)
                    return pred();
            }
            return true;
=======
            // The system time may jump while this function is waiting. To compensate for this
            // and time out near the correct time, we call do_wait_until() in a loop with a
            // short timeout and recheck the time remaining each time through the loop.
            typedef typename common_type<Duration, typename Clock::duration>::type common_duration;
            while (!pred())
            {
                common_duration d(t - Clock::now());
                if (d <= common_duration::zero()) break; // timeout occurred
                d = (std::min)(d, common_duration(chrono::milliseconds(BOOST_THREAD_POLL_INTERVAL_MILLISECONDS)));
                do_wait_until(lock, detail::internal_platform_clock::now() + detail::platform_duration(d));
            }
            return pred();
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        template <class lock_type, class Rep, class Period, class Predicate>
        bool
        wait_for(
                lock_type& lock,
                const chrono::duration<Rep, Period>& d,
                Predicate pred)
        {
<<<<<<< HEAD
          return wait_until(lock, chrono::steady_clock::now() + d, boost::move(pred));
=======
            return wait_until(lock, chrono::steady_clock::now() + d, boost::move(pred));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }
#endif

        void notify_one() BOOST_NOEXCEPT
        {
            boost::pthread::pthread_mutex_scoped_lock internal_lock(&internal_mutex);
<<<<<<< HEAD
            BOOST_VERIFY(!pthread_cond_signal(&cond));
=======
            BOOST_VERIFY(!posix::pthread_cond_signal(&cond));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        void notify_all() BOOST_NOEXCEPT
        {
            boost::pthread::pthread_mutex_scoped_lock internal_lock(&internal_mutex);
<<<<<<< HEAD
            BOOST_VERIFY(!pthread_cond_broadcast(&cond));
        }
    private: // used by boost::thread::try_join_until

        template <class lock_type>
        bool do_wait_until(
          lock_type& m,
          struct timespec const &timeout)
=======
            BOOST_VERIFY(!posix::pthread_cond_broadcast(&cond));
        }
    private:

        // When this function returns true:
        // * A notification (or sometimes a spurious OS signal) has been received
        // * Do not assume that the timeout has not been reached
        // * Do not assume that the predicate has been changed
        //
        // When this function returns false:
        // * The timeout has been reached
        // * Do not assume that a notification has not been received
        // * Do not assume that the predicate has not been changed
        template <class lock_type>
        bool do_wait_until(
          lock_type& m,
          detail::internal_platform_timepoint const &timeout)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
          int res=0;
          {
              thread_cv_detail::lock_on_exit<lock_type> guard;
#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
              detail::interruption_checker check_for_interruption(&internal_mutex,&cond);
#else
              boost::pthread::pthread_mutex_scoped_lock check_for_interruption(&internal_mutex);
#endif
              guard.activate(m);
<<<<<<< HEAD
              res=pthread_cond_timedwait(&cond,&internal_mutex,&timeout);
=======
              res=posix::pthread_cond_timedwait(&cond,&internal_mutex,&timeout.getTs());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
              check_for_interruption.unlock_if_locked();
              guard.deactivate();
          }
#if defined BOOST_THREAD_PROVIDES_INTERRUPTIONS
          this_thread::interruption_point();
#endif
          if(res==ETIMEDOUT)
          {
              return false;
          }
          if(res)
          {
              boost::throw_exception(condition_error(res, "boost::condition_variable_any::do_wait_until() failed in pthread_cond_timedwait"));
          }
          return true;
        }
    };
<<<<<<< HEAD

=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

#include <boost/config/abi_suffix.hpp>

#endif
