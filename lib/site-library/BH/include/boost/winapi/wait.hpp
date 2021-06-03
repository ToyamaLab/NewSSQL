/*
 * Copyright 2010 Vicente J. Botet Escriba
 * Copyright 2015 Andrey Semashev
 * Copyright 2017 James E. King, III
 *
 * Distributed under the Boost Software License, Version 1.0.
 * See http://www.boost.org/LICENSE_1_0.txt
 */

#ifndef BOOST_WINAPI_WAIT_HPP_INCLUDED_
#define BOOST_WINAPI_WAIT_HPP_INCLUDED_

#include <boost/winapi/basic_types.hpp>

#ifdef BOOST_HAS_PRAGMA_ONCE
#pragma once
#endif

#if !defined( BOOST_USE_WINDOWS_H )
extern "C" {

#if BOOST_WINAPI_PARTITION_APP || BOOST_WINAPI_PARTITION_SYSTEM
<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
WaitForSingleObjectEx(
    boost::winapi::HANDLE_ hHandle,
    boost::winapi::DWORD_ dwMilliseconds,
    boost::winapi::BOOL_ bAlertable);
#endif

#if BOOST_WINAPI_PARTITION_DESKTOP || BOOST_WINAPI_PARTITION_SYSTEM
#if BOOST_USE_WINAPI_VERSION >= BOOST_WINAPI_VERSION_NT4
<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
SignalObjectAndWait(
    boost::winapi::HANDLE_ hObjectToSignal,
    boost::winapi::HANDLE_ hObjectToWaitOn,
    boost::winapi::DWORD_ dwMilliseconds,
    boost::winapi::BOOL_ bAlertable);
#endif
#endif

#if BOOST_WINAPI_PARTITION_APP_SYSTEM
<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
WaitForSingleObject(
    boost::winapi::HANDLE_ hHandle,
    boost::winapi::DWORD_ dwMilliseconds);

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
WaitForMultipleObjects(
    boost::winapi::DWORD_ nCount,
    boost::winapi::HANDLE_ const* lpHandles,
    boost::winapi::BOOL_ bWaitAll,
    boost::winapi::DWORD_ dwMilliseconds);

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
WaitForMultipleObjectsEx(
    boost::winapi::DWORD_ nCount,
    boost::winapi::HANDLE_ const* lpHandles,
    boost::winapi::BOOL_ bWaitAll,
    boost::winapi::DWORD_ dwMilliseconds,
    boost::winapi::BOOL_ bAlertable);
#endif // BOOST_WINAPI_PARTITION_APP_SYSTEM

} // extern "C"
#endif

namespace boost {
namespace winapi {

#if BOOST_WINAPI_PARTITION_APP || BOOST_WINAPI_PARTITION_SYSTEM
using ::WaitForSingleObjectEx;
#endif
#if BOOST_WINAPI_PARTITION_DESKTOP || BOOST_WINAPI_PARTITION_SYSTEM
#if BOOST_USE_WINAPI_VERSION >= BOOST_WINAPI_VERSION_NT4
using ::SignalObjectAndWait;
#endif
#endif

#if BOOST_WINAPI_PARTITION_APP_SYSTEM
using ::WaitForMultipleObjects;
using ::WaitForMultipleObjectsEx;
using ::WaitForSingleObject;
#endif

#if defined( BOOST_USE_WINDOWS_H )

<<<<<<< HEAD
const DWORD_ INFINITE_ = INFINITE;
const DWORD_ WAIT_ABANDONED_ = WAIT_ABANDONED;
const DWORD_ WAIT_OBJECT_0_ = WAIT_OBJECT_0;
const DWORD_ WAIT_TIMEOUT_ = WAIT_TIMEOUT;
const DWORD_ WAIT_FAILED_ = WAIT_FAILED;

#else // defined( BOOST_USE_WINDOWS_H )

const DWORD_ INFINITE_ = (DWORD_)0xFFFFFFFF;
const DWORD_ WAIT_ABANDONED_ = 0x00000080L;
const DWORD_ WAIT_OBJECT_0_ = 0x00000000L;
const DWORD_ WAIT_TIMEOUT_ = 0x00000102L;
const DWORD_ WAIT_FAILED_ = (DWORD_)0xFFFFFFFF;

#endif // defined( BOOST_USE_WINDOWS_H )

const DWORD_ infinite = INFINITE_;
const DWORD_ wait_abandoned = WAIT_ABANDONED_;
const DWORD_ wait_object_0 = WAIT_OBJECT_0_;
const DWORD_ wait_timeout = WAIT_TIMEOUT_;
const DWORD_ wait_failed = WAIT_FAILED_;

const DWORD_ max_non_infinite_wait = (DWORD_)0xFFFFFFFE;
=======
BOOST_CONSTEXPR_OR_CONST DWORD_ INFINITE_ = INFINITE;
BOOST_CONSTEXPR_OR_CONST DWORD_ WAIT_ABANDONED_ = WAIT_ABANDONED;
BOOST_CONSTEXPR_OR_CONST DWORD_ WAIT_OBJECT_0_ = WAIT_OBJECT_0;
BOOST_CONSTEXPR_OR_CONST DWORD_ WAIT_TIMEOUT_ = WAIT_TIMEOUT;
BOOST_CONSTEXPR_OR_CONST DWORD_ WAIT_FAILED_ = WAIT_FAILED;

#else // defined( BOOST_USE_WINDOWS_H )

BOOST_CONSTEXPR_OR_CONST DWORD_ INFINITE_ = (DWORD_)0xFFFFFFFF;
BOOST_CONSTEXPR_OR_CONST DWORD_ WAIT_ABANDONED_ = 0x00000080L;
BOOST_CONSTEXPR_OR_CONST DWORD_ WAIT_OBJECT_0_ = 0x00000000L;
BOOST_CONSTEXPR_OR_CONST DWORD_ WAIT_TIMEOUT_ = 0x00000102L;
BOOST_CONSTEXPR_OR_CONST DWORD_ WAIT_FAILED_ = (DWORD_)0xFFFFFFFF;

#endif // defined( BOOST_USE_WINDOWS_H )

BOOST_CONSTEXPR_OR_CONST DWORD_ infinite = INFINITE_;
BOOST_CONSTEXPR_OR_CONST DWORD_ wait_abandoned = WAIT_ABANDONED_;
BOOST_CONSTEXPR_OR_CONST DWORD_ wait_object_0 = WAIT_OBJECT_0_;
BOOST_CONSTEXPR_OR_CONST DWORD_ wait_timeout = WAIT_TIMEOUT_;
BOOST_CONSTEXPR_OR_CONST DWORD_ wait_failed = WAIT_FAILED_;

BOOST_CONSTEXPR_OR_CONST DWORD_ max_non_infinite_wait = (DWORD_)0xFFFFFFFE;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

}
}

#endif // BOOST_WINAPI_WAIT_HPP_INCLUDED_
