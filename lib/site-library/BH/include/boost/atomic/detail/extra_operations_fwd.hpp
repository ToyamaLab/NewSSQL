/*
 * Distributed under the Boost Software License, Version 1.0.
 * (See accompanying file LICENSE_1_0.txt or copy at
 * http://www.boost.org/LICENSE_1_0.txt)
 *
 * Copyright (c) 2017 Andrey Semashev
 */
/*!
 * \file   atomic/detail/extra_operations_fwd.hpp
 *
 * This header contains forward declaration of the \c extra_operations template.
 */

#ifndef BOOST_ATOMIC_DETAIL_EXTRA_OPERATIONS_FWD_HPP_INCLUDED_
#define BOOST_ATOMIC_DETAIL_EXTRA_OPERATIONS_FWD_HPP_INCLUDED_

#include <cstddef>
#include <boost/atomic/detail/config.hpp>

#ifdef BOOST_HAS_PRAGMA_ONCE
#pragma once
#endif

namespace boost {
namespace atomics {
namespace detail {

<<<<<<< HEAD
template< typename Base, std::size_t Size, bool Signed >
=======
template< typename Base, std::size_t Size, bool Signed, bool = Base::is_always_lock_free >
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
struct extra_operations;

} // namespace detail
} // namespace atomics
} // namespace boost

#endif // BOOST_ATOMIC_DETAIL_EXTRA_OPERATIONS_FWD_HPP_INCLUDED_
