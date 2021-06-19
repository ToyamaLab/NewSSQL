/*
 * Distributed under the Boost Software License, Version 1.0.
 * (See accompanying file LICENSE_1_0.txt or copy at
 * http://www.boost.org/LICENSE_1_0.txt)
 *
 * Copyright (c) 2017 Andrey Semashev
 */
/*!
 * \file   atomic/detail/type_traits/conditional.hpp
 *
 * This header defines \c conditional type trait
 */

#ifndef BOOST_ATOMIC_DETAIL_TYPE_TRAITS_CONDITIONAL_HPP_INCLUDED_
#define BOOST_ATOMIC_DETAIL_TYPE_TRAITS_CONDITIONAL_HPP_INCLUDED_

#include <boost/atomic/detail/config.hpp>
<<<<<<< HEAD
#if !defined(BOOST_ATOMIC_DETAIL_NO_CXX11_HDR_TYPE_TRAITS)
=======
#if !defined(BOOST_ATOMIC_DETAIL_NO_CXX11_BASIC_HDR_TYPE_TRAITS)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <type_traits>
#else
#include <boost/type_traits/conditional.hpp>
#endif

#ifdef BOOST_HAS_PRAGMA_ONCE
#pragma once
#endif

namespace boost {
namespace atomics {
namespace detail {

<<<<<<< HEAD
#if !defined(BOOST_ATOMIC_DETAIL_NO_CXX11_HDR_TYPE_TRAITS)
=======
#if !defined(BOOST_ATOMIC_DETAIL_NO_CXX11_BASIC_HDR_TYPE_TRAITS)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
using std::conditional;
#else
using boost::conditional;
#endif

} // namespace detail
} // namespace atomics
} // namespace boost

#endif // BOOST_ATOMIC_DETAIL_TYPE_TRAITS_CONDITIONAL_HPP_INCLUDED_
