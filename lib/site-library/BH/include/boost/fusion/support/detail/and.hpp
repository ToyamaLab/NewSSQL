/*=============================================================================
    Copyright (c) 2016 Lee Clagett
<<<<<<< HEAD
=======
    Copyright (c) 2018 Kohei Takahashi
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

    Distributed under the Boost Software License, Version 1.0. (See accompanying
    file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
==============================================================================*/
#ifndef FUSION_AND_07152016_1625
#define FUSION_AND_07152016_1625

#include <boost/config.hpp>
<<<<<<< HEAD
=======
#include <boost/config/workaround.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/type_traits/integral_constant.hpp>

#if defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
#error fusion::detail::and_ requires variadic templates
#endif

namespace boost { namespace fusion { namespace detail {
<<<<<<< HEAD
=======
#if defined(BOOST_NO_CXX17_FOLD_EXPRESSIONS) \
 || BOOST_WORKAROUND(BOOST_MSVC, BOOST_TESTED_AT(1913))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    template<typename ...Cond>
    struct and_impl : false_type {};

    template<typename ...T>
    struct and_impl<integral_constant<T, true>...> : true_type {};

    // This specialization is necessary to avoid MSVC-12 variadics bug.
    template<bool ...Cond>
    struct and_impl1 : and_impl<integral_constant<bool, Cond>...> {};

    /* fusion::detail::and_ differs from mpl::and_ in the following ways:
       - The empty set is valid and returns true
       - A single element set is valid and returns the identity
       - There is no upper bound on the set size
       - The conditions are evaluated at once, and are not short-circuited. This
         reduces instantations when returning true; the implementation is not
         recursive. */
    template<typename ...Cond>
    struct and_ : and_impl1<Cond::value...> {};
<<<<<<< HEAD
=======
#else
    template <typename ...Cond>
    struct and_ : integral_constant<bool, ((bool)Cond::value && ...)> {};
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}}}

#endif // FUSION_AND_07152016_1625
