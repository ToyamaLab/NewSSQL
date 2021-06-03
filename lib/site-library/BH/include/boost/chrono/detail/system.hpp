//  Copyright 2009-2010 Vicente J. Botet Escriba

//  Distributed under the Boost Software License, Version 1.0.
//  See http://www.boost.org/LICENSE_1_0.txt

#ifndef BOOST_CHRONO_DETAIL_SYSTEM_HPP
#define BOOST_CHRONO_DETAIL_SYSTEM_HPP

#if !defined BOOST_CHRONO_DONT_PROVIDE_HYBRID_ERROR_HANDLING

#include <boost/system/error_code.hpp>

<<<<<<< HEAD
#define BOOST_CHRONO_SYSTEM_CATEGORY boost::system::system_category()

#define BOOST_CHRONO_THROWS boost::throws()
#define BOOST_CHRONO_IS_THROWS(EC) (&EC==&boost::throws())
=======
namespace boost {
namespace chrono {
    inline bool is_throws(system::error_code  & ec) { return (&ec==&boost::throws()); }
}
}
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#endif
#endif
