/*
 * Copyright 2010 Vicente J. Botet Escriba
 * Copyright 2015 Andrey Semashev
 *
 * Distributed under the Boost Software License, Version 1.0.
 * See http://www.boost.org/LICENSE_1_0.txt
 */

#ifndef BOOST_WINAPI_LOCAL_MEMORY_HPP_INCLUDED_
#define BOOST_WINAPI_LOCAL_MEMORY_HPP_INCLUDED_

#include <boost/winapi/basic_types.hpp>

#ifdef BOOST_HAS_PRAGMA_ONCE
#pragma once
#endif

#if BOOST_WINAPI_PARTITION_APP_SYSTEM

#if !defined( BOOST_USE_WINDOWS_H )
namespace boost { namespace winapi {
typedef HANDLE_ HLOCAL_;
}}

extern "C" {
<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::HLOCAL_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::HLOCAL_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
LocalAlloc(
    boost::winapi::UINT_ uFlags,
    boost::winapi::SIZE_T_ uBytes);

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::HLOCAL_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::HLOCAL_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
LocalReAlloc(
    boost::winapi::HLOCAL_ hMem,
    boost::winapi::SIZE_T_ uBytes,
    boost::winapi::UINT_ uFlags);

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::HLOCAL_ WINAPI LocalFree(boost::winapi::HLOCAL_ hMem);
=======
BOOST_SYMBOL_IMPORT boost::winapi::HLOCAL_ BOOST_WINAPI_WINAPI_CC LocalFree(boost::winapi::HLOCAL_ hMem);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
} // extern "C"
#endif

namespace boost {
namespace winapi {
#if defined( BOOST_USE_WINDOWS_H )
typedef ::HLOCAL HLOCAL_;
#endif
using ::LocalAlloc;
using ::LocalReAlloc;
using ::LocalFree;
}
}

#endif // BOOST_WINAPI_PARTITION_APP_SYSTEM
#endif // BOOST_WINAPI_LOCAL_MEMORY_HPP_INCLUDED_
