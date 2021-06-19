<<<<<<< HEAD
// Copyright Daniel Wallin, David Abrahams 2005. Use, modification and
// distribution is subject to the Boost Software License, Version 1.0. (See
// accompanying file LICENSE_1_0.txt or copy at
=======
// Copyright Daniel Wallin, David Abrahams 2005.
// Distributed under the Boost Software License, Version 1.0.
// (See accompanying file LICENSE_1_0.txt or copy at
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_PARAMETER_VOID_050329_HPP
#define BOOST_PARAMETER_VOID_050329_HPP

namespace boost { namespace parameter { 

<<<<<<< HEAD
// A placemarker for "no argument passed."
// MAINTAINER NOTE: Do not make this into a metafunction
struct void_ {}; 

namespace aux 
{

  inline void_& void_reference()
  {
      static void_ instance;
      return instance;
  }

} // namespace aux

}} // namespace boost::parameter

#endif // BOOST_PARAMETER_VOID_050329_HPP
=======
    // A placemarker for "no argument passed."
    // MAINTAINER NOTE: Do not make this into a metafunction
    struct void_
    {
    };
}} // namespace boost::parameter

namespace boost { namespace parameter { namespace aux {

    inline ::boost::parameter::void_& void_reference()
    {
        static ::boost::parameter::void_ instance;
        return instance;
    }
}}} // namespace boost::parameter::aux

#include <boost/config/workaround.hpp>

#if BOOST_WORKAROUND(__SUNPRO_CC, BOOST_TESTED_AT(0x580))

namespace boost { namespace parameter { namespace aux {

    typedef void* voidstar;
}}} // namespace boost::parameter::aux

#endif
#endif  // include guard
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

