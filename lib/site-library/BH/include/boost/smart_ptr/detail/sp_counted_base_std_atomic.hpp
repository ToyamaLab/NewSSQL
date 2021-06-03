#ifndef BOOST_SMART_PTR_DETAIL_SP_COUNTED_BASE_STD_ATOMIC_HPP_INCLUDED
#define BOOST_SMART_PTR_DETAIL_SP_COUNTED_BASE_STD_ATOMIC_HPP_INCLUDED

// MS compatible compilers support #pragma once

#if defined(_MSC_VER) && (_MSC_VER >= 1020)
# pragma once
#endif

//  detail/sp_counted_base_std_atomic.hpp - C++11 std::atomic
//
//  Copyright (c) 2007, 2013 Peter Dimov
//
//  Distributed under the Boost Software License, Version 1.0.
//  See accompanying file LICENSE_1_0.txt or copy at
//  http://www.boost.org/LICENSE_1_0.txt

<<<<<<< HEAD
#include <boost/detail/sp_typeinfo.hpp>
=======
#include <boost/smart_ptr/detail/sp_typeinfo_.hpp>
#include <boost/smart_ptr/detail/sp_noexcept.hpp>
#include <boost/config.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <atomic>
#include <cstdint>

namespace boost
{

namespace detail
{

<<<<<<< HEAD
inline void atomic_increment( std::atomic_int_least32_t * pw )
=======
inline void atomic_increment( std::atomic_int_least32_t * pw ) BOOST_SP_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    pw->fetch_add( 1, std::memory_order_relaxed );
}

<<<<<<< HEAD
inline std::int_least32_t atomic_decrement( std::atomic_int_least32_t * pw )
=======
inline std::int_least32_t atomic_decrement( std::atomic_int_least32_t * pw ) BOOST_SP_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    return pw->fetch_sub( 1, std::memory_order_acq_rel );
}

<<<<<<< HEAD
inline std::int_least32_t atomic_conditional_increment( std::atomic_int_least32_t * pw )
=======
inline std::int_least32_t atomic_conditional_increment( std::atomic_int_least32_t * pw ) BOOST_SP_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    // long r = *pw;
    // if( r != 0 ) ++*pw;
    // return r;

    std::int_least32_t r = pw->load( std::memory_order_relaxed );

    for( ;; )
    {
        if( r == 0 )
        {
            return r;
        }

        if( pw->compare_exchange_weak( r, r + 1, std::memory_order_relaxed, std::memory_order_relaxed ) )
        {
            return r;
        }
    }    
}

<<<<<<< HEAD
class sp_counted_base
=======
class BOOST_SYMBOL_VISIBLE sp_counted_base
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
private:

    sp_counted_base( sp_counted_base const & );
    sp_counted_base & operator= ( sp_counted_base const & );

<<<<<<< HEAD
    std::atomic_int_least32_t use_count_;	// #shared
    std::atomic_int_least32_t weak_count_;	// #weak + (#shared != 0)

public:

    sp_counted_base(): use_count_( 1 ), weak_count_( 1 )
    {
    }

    virtual ~sp_counted_base() // nothrow
=======
    std::atomic_int_least32_t use_count_;   // #shared
    std::atomic_int_least32_t weak_count_;  // #weak + (#shared != 0)

public:

    sp_counted_base() BOOST_SP_NOEXCEPT: use_count_( 1 ), weak_count_( 1 )
    {
    }

    virtual ~sp_counted_base() /*BOOST_SP_NOEXCEPT*/
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
    }

    // dispose() is called when use_count_ drops to zero, to release
    // the resources managed by *this.

<<<<<<< HEAD
    virtual void dispose() = 0; // nothrow

    // destroy() is called when weak_count_ drops to zero.

    virtual void destroy() // nothrow
=======
    virtual void dispose() BOOST_SP_NOEXCEPT = 0;

    // destroy() is called when weak_count_ drops to zero.

    virtual void destroy() BOOST_SP_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        delete this;
    }

<<<<<<< HEAD
    virtual void * get_deleter( sp_typeinfo const & ti ) = 0;
    virtual void * get_local_deleter( sp_typeinfo const & ti ) = 0;
    virtual void * get_untyped_deleter() = 0;

    void add_ref_copy()
=======
    virtual void * get_deleter( sp_typeinfo_ const & ti ) BOOST_SP_NOEXCEPT = 0;
    virtual void * get_local_deleter( sp_typeinfo_ const & ti ) BOOST_SP_NOEXCEPT = 0;
    virtual void * get_untyped_deleter() BOOST_SP_NOEXCEPT = 0;

    void add_ref_copy() BOOST_SP_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        atomic_increment( &use_count_ );
    }

<<<<<<< HEAD
    bool add_ref_lock() // true on success
=======
    bool add_ref_lock() BOOST_SP_NOEXCEPT // true on success
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        return atomic_conditional_increment( &use_count_ ) != 0;
    }

<<<<<<< HEAD
    void release() // nothrow
=======
    void release() BOOST_SP_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        if( atomic_decrement( &use_count_ ) == 1 )
        {
            dispose();
            weak_release();
        }
    }

<<<<<<< HEAD
    void weak_add_ref() // nothrow
=======
    void weak_add_ref() BOOST_SP_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        atomic_increment( &weak_count_ );
    }

<<<<<<< HEAD
    void weak_release() // nothrow
=======
    void weak_release() BOOST_SP_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        if( atomic_decrement( &weak_count_ ) == 1 )
        {
            destroy();
        }
    }

<<<<<<< HEAD
    long use_count() const // nothrow
=======
    long use_count() const BOOST_SP_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        return use_count_.load( std::memory_order_acquire );
    }
};

} // namespace detail

} // namespace boost

#endif  // #ifndef BOOST_SMART_PTR_DETAIL_SP_COUNTED_BASE_STD_ATOMIC_HPP_INCLUDED
