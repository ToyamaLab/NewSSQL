//-----------------------------------------------------------------------------
// boost variant/detail/forced_return.hpp header file
// See http://www.boost.org for updates, documentation, and revision history.
//-----------------------------------------------------------------------------
//
// Copyright (c) 2003 Eric Friedman
<<<<<<< HEAD
// Copyright (c) 2015-2016 Antony Polukhin
=======
// Copyright (c) 2015-2019 Antony Polukhin
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
//
// Distributed under the Boost Software License, Version 1.0. (See
// accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_VARIANT_DETAIL_FORCED_RETURN_HPP
#define BOOST_VARIANT_DETAIL_FORCED_RETURN_HPP

#include <boost/config.hpp>
<<<<<<< HEAD
#include <boost/variant/detail/generic_result_type.hpp>
#include <boost/assert.hpp>
#include <cstdlib> // std::abort
#ifndef R_NO_REMAP
#define R_NO_REMAP
#endif
#include <R_ext/Error.h>        // Rf_error()
=======
#include <boost/assert.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce


#ifdef BOOST_MSVC
# pragma warning( push )
# pragma warning( disable : 4702 ) // unreachable code
#endif

namespace boost { namespace detail { namespace variant {

<<<<<<< HEAD
BOOST_NORETURN inline void forced_return_no_return() { // fixes `must return a value` warnings
    using namespace std;
    ::Rf_error("Forced return to R");
    //abort(); // some implementations have no std::abort
    
}


=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
///////////////////////////////////////////////////////////////////////////////
// (detail) function template forced_return
//
// Logical error to permit invocation at runtime, but (artificially) satisfies
// compile-time requirement of returning a result value.
//
template <typename T>
<<<<<<< HEAD
BOOST_NORETURN inline
    BOOST_VARIANT_AUX_GENERIC_RESULT_TYPE(T)
=======
BOOST_NORETURN inline T
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
forced_return()
{
    // logical error: should never be here! (see above)
    BOOST_ASSERT(false);

<<<<<<< HEAD
    forced_return_no_return();

#ifdef BOOST_NO_NORETURN
    BOOST_VARIANT_AUX_GENERIC_RESULT_TYPE(T) (*dummy)() = 0;
    return dummy();
#endif
=======
    T (*dummy)() = 0;
    (void)dummy;
    BOOST_UNREACHABLE_RETURN(dummy());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

}}} // namespace boost::detail::variant


#ifdef BOOST_MSVC
# pragma warning( pop )
#endif

#endif // BOOST_VARIANT_DETAIL_FORCED_RETURN_HPP
