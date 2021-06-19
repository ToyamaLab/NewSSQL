/*
<<<<<<< HEAD
 * Distributed under the Boost Software License, Version 1.0.(See accompanying 
 * file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt.)
 * 
=======
 * Distributed under the Boost Software License, Version 1.0.(See accompanying
 * file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt.)
 *
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
 * See http://www.boost.org/libs/iostreams for documentation.

 * File:        boost/iostreams/detail/execute.hpp
 * Date:        Thu Dec 06 13:21:54 MST 2007
 * Copyright:   2007-2008 CodeRage, LLC
 * Author:      Jonathan Turkanis
 * Contact:     turkanis at coderage dot com
 *
 * Defines the preprocessor symbol BOOST_IOSTREAMS_HAS_DINKUMWARE_FPOS for
<<<<<<< HEAD
 * platforms that use the implementation of std::fpos from the Dinkumware 
=======
 * platforms that use the implementation of std::fpos from the Dinkumware
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
 * Standard Library.
 */

#ifndef BOOST_IOSTREAMS_DETAIL_CONFIG_FPOS_HPP_INCLUDED
#define BOOST_IOSTREAMS_DETAIL_CONFIG_FPOS_HPP_INCLUDED

#if defined(_MSC_VER)
# pragma once
#endif

#include <boost/config.hpp>

# if (defined(_YVALS) || defined(_CPPLIB_VER)) && !defined(__SGI_STL_PORT) && \
<<<<<<< HEAD
     !defined(_STLPORT_VERSION) && !defined(__QNX__) && !defined(_VX_CPU)
     /**/
     
=======
     !defined(_STLPORT_VERSION) && !defined(__QNX__) && !defined(_VX_CPU) && !defined(__VXWORKS__) \
     && !((defined(BOOST_MSVC) || defined(BOOST_CLANG)) && _MSVC_STL_VERSION >= 141)
     /**/

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/iostreams/detail/ios.hpp>

#  define BOOST_IOSTREAMS_HAS_DINKUMWARE_FPOS

#if !defined(_FPOSOFF)
#define BOOST_IOSTREAMS_FPOSOFF(fp) ((long long)(fp))
#else
#define BOOST_IOSTREAMS_FPOSOFF(fp) _FPOSOFF(fp)
#endif

# endif

#endif // #ifndef BOOST_IOSTREAMS_DETAIL_CONFIG_FPOS_HPP_INCLUDED
