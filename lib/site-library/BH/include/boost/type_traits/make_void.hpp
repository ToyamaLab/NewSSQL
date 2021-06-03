/*
Copyright 2017 Glen Joseph Fernandes
<<<<<<< HEAD
<glenjofe -at- gmail.com>
=======
(glenjofe@gmail.com)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

Distributed under the Boost Software License,
Version 1.0. (See accompanying file LICENSE_1_0.txt
or copy at http://www.boost.org/LICENSE_1_0.txt)
*/

#ifndef BOOST_TT_MAKE_VOID_HPP_INCLUDED
#define BOOST_TT_MAKE_VOID_HPP_INCLUDED

#include <boost/config.hpp>

namespace boost {

#if !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
template<class...>
struct make_void {
    typedef void type;
};

#if !defined(BOOST_NO_CXX11_TEMPLATE_ALIASES)
template<class... Ts>
using void_t = typename make_void<Ts...>::type;
#endif

#else /* BOOST_NO_CXX11_VARIADIC_TEMPLATES */

template<class = void,
         class = void,
         class = void,
         class = void,
         class = void>
struct make_void {
    typedef void type;
};

#if !defined(BOOST_NO_CXX11_TEMPLATE_ALIASES)
template<class A = void,
         class B = void,
         class C = void,
         class D = void,
         class E = void>
using void_t = typename make_void<A, B, C, D, E>::type;
#endif

#endif

} /* boost */

#endif
