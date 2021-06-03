/*
 * Copyright 2017 James E. King, III
 *
 * Distributed under the Boost Software License, Version 1.0.
 * See http://www.boost.org/LICENSE_1_0.txt
 */

#ifndef BOOST_WINAPI_BCRYPT_HPP_INCLUDED_
#define BOOST_WINAPI_BCRYPT_HPP_INCLUDED_

#include <boost/winapi/basic_types.hpp>

#ifdef BOOST_HAS_PRAGMA_ONCE
#pragma once
#endif

<<<<<<< HEAD
#if defined(BOOST_WINAPI_IS_MINGW_W64) || (BOOST_USE_WINAPI_VERSION >= BOOST_WINAPI_VERSION_WIN6)
=======
#if BOOST_USE_WINAPI_VERSION >= BOOST_WINAPI_VERSION_WIN6
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#if BOOST_WINAPI_PARTITION_APP_SYSTEM

#if defined(BOOST_USE_WINDOWS_H)

#include <bcrypt.h>

namespace boost { namespace winapi {
typedef ::BCRYPT_ALG_HANDLE BCRYPT_ALG_HANDLE_;
}}

#else // defined(BOOST_USE_WINDOWS_H)

namespace boost { namespace winapi {
typedef PVOID_ BCRYPT_ALG_HANDLE_;
}}

extern "C" {

<<<<<<< HEAD
boost::winapi::NTSTATUS_ WINAPI
=======
boost::winapi::NTSTATUS_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
BCryptCloseAlgorithmProvider(
    boost::winapi::BCRYPT_ALG_HANDLE_ hAlgorithm,
    boost::winapi::ULONG_             dwFlags
);

<<<<<<< HEAD
boost::winapi::NTSTATUS_ WINAPI
=======
boost::winapi::NTSTATUS_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
BCryptGenRandom(
    boost::winapi::BCRYPT_ALG_HANDLE_ hAlgorithm,
    boost::winapi::PUCHAR_            pbBuffer,
    boost::winapi::ULONG_             cbBuffer,
    boost::winapi::ULONG_             dwFlags
);

<<<<<<< HEAD
boost::winapi::NTSTATUS_ WINAPI
=======
boost::winapi::NTSTATUS_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
BCryptOpenAlgorithmProvider(
    boost::winapi::BCRYPT_ALG_HANDLE_ *phAlgorithm,
    boost::winapi::LPCWSTR_           pszAlgId,
    boost::winapi::LPCWSTR_           pszImplementation,
    boost::winapi::DWORD_             dwFlags
);

} // extern "C"

#endif // defined(BOOST_USE_WINDOWS_H)

namespace boost {
namespace winapi {

#if defined(BOOST_USE_WINDOWS_H)
const WCHAR_ BCRYPT_RNG_ALGORITHM_[] = BCRYPT_RNG_ALGORITHM;
#else
const WCHAR_ BCRYPT_RNG_ALGORITHM_[] = L"RNG";
#endif

using ::BCryptCloseAlgorithmProvider;
using ::BCryptGenRandom;
using ::BCryptOpenAlgorithmProvider;

} // winapi
} // boost

#endif // BOOST_WINAPI_PARTITION_APP_SYSTEM

<<<<<<< HEAD
#endif // defined(BOOST_WINAPI_IS_MINGW_W64) || (BOOST_USE_WINAPI_VERSION >= BOOST_WINAPI_VERSION_WIN6)
=======
#endif // BOOST_USE_WINAPI_VERSION >= BOOST_WINAPI_VERSION_WIN6
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#endif // BOOST_WINAPI_BCRYPT_HPP_INCLUDED_
