//////////////////////////////////////////////////////////////////////////////
//
// (C) Copyright Ion Gaztanaga 2014-2015. Distributed under the Boost
// Software License, Version 1.0. (See accompanying file
// LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
//
// See http://www.boost.org/libs/container for documentation.
//
//////////////////////////////////////////////////////////////////////////////
#ifndef BOOST_CONTAINER_DETAIL_ALLOC_TRAITS_HPP
#define BOOST_CONTAINER_DETAIL_ALLOC_TRAITS_HPP

#ifndef BOOST_CONFIG_HPP
#  include <boost/config.hpp>
#endif

#if defined(BOOST_HAS_PRAGMA_ONCE)
#  pragma once
#endif

// move
#include <boost/move/adl_move_swap.hpp>
#include <boost/move/utility_core.hpp>

namespace boost {
namespace container {
<<<<<<< HEAD
namespace container_detail {

template<class AllocatorType>
inline void swap_alloc(AllocatorType &, AllocatorType &, container_detail::false_type)
=======
namespace dtl {

template<class AllocatorType>
inline void swap_alloc(AllocatorType &, AllocatorType &, dtl::false_type)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   BOOST_NOEXCEPT_OR_NOTHROW
{}

template<class AllocatorType>
<<<<<<< HEAD
inline void swap_alloc(AllocatorType &l, AllocatorType &r, container_detail::true_type)
{  boost::adl_move_swap(l, r);   }

template<class AllocatorType>
inline void assign_alloc(AllocatorType &, const AllocatorType &, container_detail::false_type)
=======
inline void swap_alloc(AllocatorType &l, AllocatorType &r, dtl::true_type)
{  boost::adl_move_swap(l, r);   }

template<class AllocatorType>
inline void assign_alloc(AllocatorType &, const AllocatorType &, dtl::false_type)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   BOOST_NOEXCEPT_OR_NOTHROW
{}

template<class AllocatorType>
<<<<<<< HEAD
inline void assign_alloc(AllocatorType &l, const AllocatorType &r, container_detail::true_type)
{  l = r;   }

template<class AllocatorType>
inline void move_alloc(AllocatorType &, AllocatorType &, container_detail::false_type)
=======
inline void assign_alloc(AllocatorType &l, const AllocatorType &r, dtl::true_type)
{  l = r;   }

template<class AllocatorType>
inline void move_alloc(AllocatorType &, AllocatorType &, dtl::false_type)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
   BOOST_NOEXCEPT_OR_NOTHROW
{}

template<class AllocatorType>
<<<<<<< HEAD
inline void move_alloc(AllocatorType &l, AllocatorType &r, container_detail::true_type)
{  l = ::boost::move(r);   }

}  //namespace container_detail {
=======
inline void move_alloc(AllocatorType &l, AllocatorType &r, dtl::true_type)
{  l = ::boost::move(r);   }

}  //namespace dtl {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}  //namespace container {
}  //namespace boost {

#endif   //#ifndef BOOST_CONTAINER_DETAIL_ALLOC_TRAITS_HPP
