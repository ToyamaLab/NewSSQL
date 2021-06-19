///////////////////////////////////////////////////////////////
//  Copyright 2012 John Maddock. Distributed under the Boost
//  Software License, Version 1.0. (See accompanying file
<<<<<<< HEAD
//  LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_
=======
//  LICENSE_1_0.txt or copy at https://www.boost.org/LICENSE_1_0.txt
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#ifndef BOOST_MP_DIGITS_HPP
#define BOOST_MP_DIGITS_HPP

<<<<<<< HEAD
namespace boost{ namespace multiprecision{ namespace detail{
=======
namespace boost { namespace multiprecision { namespace detail {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

inline unsigned long digits10_2_2(unsigned long d10)
{
   return (d10 * 1000uL) / 301uL + ((d10 * 1000uL) % 301 ? 2u : 1u);
}

inline unsigned long digits2_2_10(unsigned long d2)
{
   return (d2 * 301uL) / 1000uL;
}

<<<<<<< HEAD
}}} // namespaces
=======
}}} // namespace boost::multiprecision::detail
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#endif
