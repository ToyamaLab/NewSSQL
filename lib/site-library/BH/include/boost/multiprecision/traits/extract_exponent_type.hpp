///////////////////////////////////////////////////////////////
//  Copyright 2012 John Maddock. Distributed under the Boost
//  Software License, Version 1.0. (See accompanying file
<<<<<<< HEAD
//  LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_
=======
//  LICENSE_1_0.txt or copy at https://www.boost.org/LICENSE_1_0.txt
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#ifndef BOOST_MATH_EXTRACT_EXPONENT_HPP
#define BOOST_MATH_EXTRACT_EXPONENT_HPP

#include <boost/multiprecision/number.hpp>

<<<<<<< HEAD
namespace boost{
namespace multiprecision{
namespace backends{
=======
namespace boost {
namespace multiprecision {
namespace backends {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

template <class Backend, int cat>
struct extract_exponent_type
{
   typedef int type;
};
template <class Backend>
struct extract_exponent_type<Backend, number_kind_floating_point>
{
   typedef typename Backend::exponent_type type;
};

<<<<<<< HEAD
}}} // namespaces
=======
}}} // namespace boost::multiprecision::backends
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#endif
