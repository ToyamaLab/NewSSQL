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

#ifndef YESNO_050328_HPP
#define YESNO_050328_HPP

<<<<<<< HEAD
=======
namespace boost { namespace parameter { namespace aux {

    // types used with the "sizeof trick" to capture the results of
    // overload resolution at compile-time.
    typedef char yes_tag;
    typedef char (&no_tag)[2];
}}} // namespace boost::parameter::aux

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/mpl/bool.hpp>

namespace boost { namespace parameter { namespace aux {

<<<<<<< HEAD
// types used with the "sizeof trick" to capture the results of
// overload resolution at compile-time.
typedef char yes_tag;
typedef char (&no_tag)[2];

// mpl::true_ and mpl::false_ are not distinguishable by sizeof(),
// so we pass them through these functions to get a type that is.
yes_tag to_yesno(mpl::true_);
no_tag to_yesno(mpl::false_);

}}} // namespace boost::parameter::aux

#endif // YESNO_050328_HPP
=======
    // mpl::true_ and mpl::false_ are not distinguishable by sizeof(),
    // so we pass them through these functions to get a type that is.
    ::boost::parameter::aux::yes_tag to_yesno(::boost::mpl::true_);
    ::boost::parameter::aux::no_tag to_yesno(::boost::mpl::false_);
}}} // namespace boost::parameter::aux

#include <boost/parameter/config.hpp>

#if defined(BOOST_PARAMETER_CAN_USE_MP11)
#include <boost/mp11/integral.hpp>

namespace boost { namespace parameter { namespace aux {

    // mp11::mp_true and mp11::mp_false are not distinguishable by sizeof(),
    // so we pass them through these functions to get a type that is.
    ::boost::parameter::aux::yes_tag to_yesno(::boost::mp11::mp_true);
    ::boost::parameter::aux::no_tag to_yesno(::boost::mp11::mp_false);
}}} // namespace boost::parameter::aux

#endif  // BOOST_PARAMETER_CAN_USE_MP11
#endif  // include guard
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

