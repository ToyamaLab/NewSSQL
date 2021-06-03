/*
 * Copyright 2010 Vicente J. Botet Escriba
 * Copyright 2014 Renato Tegon Forti, Antony Polukhin
 * Copyright 2015 Andrey Semashev
 * Copyright 2015 Antony Polukhin
 *
 * Distributed under the Boost Software License, Version 1.0.
 * See http://www.boost.org/LICENSE_1_0.txt
 */

#ifndef BOOST_WINAPI_DLL_HPP_INCLUDED_
#define BOOST_WINAPI_DLL_HPP_INCLUDED_

#include <boost/winapi/basic_types.hpp>

#ifdef BOOST_HAS_PRAGMA_ONCE
#pragma once
#endif

#if BOOST_WINAPI_PARTITION_DESKTOP || BOOST_WINAPI_PARTITION_SYSTEM

#if !defined( BOOST_USE_WINDOWS_H )
extern "C" {
namespace boost { namespace winapi {
#ifdef _WIN64
<<<<<<< HEAD
typedef INT_PTR_ (WINAPI *FARPROC_)();
typedef INT_PTR_ (WINAPI *NEARPROC_)();
typedef INT_PTR_ (WINAPI *PROC_)();
#else
typedef int (WINAPI *FARPROC_)();
typedef int (WINAPI *NEARPROC_)();
typedef int (WINAPI *PROC_)();
=======
typedef INT_PTR_ (BOOST_WINAPI_WINAPI_CC *FARPROC_)();
typedef INT_PTR_ (BOOST_WINAPI_WINAPI_CC *NEARPROC_)();
typedef INT_PTR_ (BOOST_WINAPI_WINAPI_CC *PROC_)();
#else
typedef int (BOOST_WINAPI_WINAPI_CC *FARPROC_)();
typedef int (BOOST_WINAPI_WINAPI_CC *NEARPROC_)();
typedef int (BOOST_WINAPI_WINAPI_CC *PROC_)();
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#endif // _WIN64
}} // namespace boost::winapi

#if !defined( BOOST_NO_ANSI_APIS )
<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::HMODULE_ WINAPI
LoadLibraryA(boost::winapi::LPCSTR_ lpFileName);

BOOST_SYMBOL_IMPORT boost::winapi::HMODULE_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::HMODULE_ BOOST_WINAPI_WINAPI_CC
LoadLibraryA(boost::winapi::LPCSTR_ lpFileName);

BOOST_SYMBOL_IMPORT boost::winapi::HMODULE_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
LoadLibraryExA(
    boost::winapi::LPCSTR_ lpFileName,
    boost::winapi::HANDLE_ hFile,
    boost::winapi::DWORD_ dwFlags
);

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::HMODULE_ WINAPI
GetModuleHandleA(boost::winapi::LPCSTR_ lpFileName);

BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::HMODULE_ BOOST_WINAPI_WINAPI_CC
GetModuleHandleA(boost::winapi::LPCSTR_ lpFileName);

BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
GetModuleFileNameA(
    boost::winapi::HMODULE_ hModule,
    boost::winapi::LPSTR_ lpFilename,
    boost::winapi::DWORD_ nSize
);
#endif

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::HMODULE_ WINAPI
LoadLibraryW(boost::winapi::LPCWSTR_ lpFileName);

BOOST_SYMBOL_IMPORT boost::winapi::HMODULE_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::HMODULE_ BOOST_WINAPI_WINAPI_CC
LoadLibraryW(boost::winapi::LPCWSTR_ lpFileName);

BOOST_SYMBOL_IMPORT boost::winapi::HMODULE_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
LoadLibraryExW(
    boost::winapi::LPCWSTR_ lpFileName,
    boost::winapi::HANDLE_ hFile,
    boost::winapi::DWORD_ dwFlags
);

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::HMODULE_ WINAPI
GetModuleHandleW(boost::winapi::LPCWSTR_ lpFileName);

BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::HMODULE_ BOOST_WINAPI_WINAPI_CC
GetModuleHandleW(boost::winapi::LPCWSTR_ lpFileName);

BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
GetModuleFileNameW(
    boost::winapi::HMODULE_ hModule,
    boost::winapi::LPWSTR_ lpFilename,
    boost::winapi::DWORD_ nSize
);

#if !defined( UNDER_CE )
<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::FARPROC_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::FARPROC_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
GetProcAddress(boost::winapi::HMODULE_ hModule, boost::winapi::LPCSTR_ lpProcName);
#else
// On Windows CE there are two functions: GetProcAddressA (since Windows CE 3.0) and GetProcAddressW.
// GetProcAddress is a macro that is _always_ defined to GetProcAddressW.
<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::FARPROC_ WINAPI
GetProcAddressA(boost::winapi::HMODULE_ hModule, boost::winapi::LPCSTR_ lpProcName);
BOOST_SYMBOL_IMPORT boost::winapi::FARPROC_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::FARPROC_ BOOST_WINAPI_WINAPI_CC
GetProcAddressA(boost::winapi::HMODULE_ hModule, boost::winapi::LPCSTR_ lpProcName);
BOOST_SYMBOL_IMPORT boost::winapi::FARPROC_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
GetProcAddressW(boost::winapi::HMODULE_ hModule, boost::winapi::LPCWSTR_ lpProcName);
#endif

struct _MEMORY_BASIC_INFORMATION;

#if !defined( BOOST_WINAPI_IS_MINGW )
<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::SIZE_T_ WINAPI
VirtualQuery(
    boost::winapi::LPCVOID_ lpAddress,
    ::_MEMORY_BASIC_INFORMATION* lpBuffer,
    boost::winapi::ULONG_PTR_ dwLength
);
#else // !defined( BOOST_WINAPI_IS_MINGW )
BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::SIZE_T_ BOOST_WINAPI_WINAPI_CC
VirtualQuery(
    boost::winapi::LPCVOID_ lpAddress,
    ::_MEMORY_BASIC_INFORMATION* lpBuffer,
    boost::winapi::SIZE_T_ dwLength
);
#else // !defined( BOOST_WINAPI_IS_MINGW )
BOOST_SYMBOL_IMPORT boost::winapi::DWORD_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
VirtualQuery(
    boost::winapi::LPCVOID_ lpAddress,
    ::_MEMORY_BASIC_INFORMATION* lpBuffer,
    boost::winapi::DWORD_ dwLength
);
#endif // !defined( BOOST_WINAPI_IS_MINGW )
} // extern "C"
#endif // #if !defined( BOOST_USE_WINDOWS_H )

namespace boost {
namespace winapi {

typedef struct BOOST_MAY_ALIAS MEMORY_BASIC_INFORMATION_ {
    PVOID_  BaseAddress;
    PVOID_  AllocationBase;
    DWORD_  AllocationProtect;
    SIZE_T_ RegionSize;
    DWORD_  State;
    DWORD_  Protect;
    DWORD_  Type;
} *PMEMORY_BASIC_INFORMATION_;

#if defined( BOOST_USE_WINDOWS_H )
typedef ::FARPROC FARPROC_;
typedef ::NEARPROC NEARPROC_;
typedef ::PROC PROC_;

<<<<<<< HEAD
const DWORD_ DONT_RESOLVE_DLL_REFERENCES_           = DONT_RESOLVE_DLL_REFERENCES;
const DWORD_ LOAD_WITH_ALTERED_SEARCH_PATH_         = LOAD_WITH_ALTERED_SEARCH_PATH;
#else // defined( BOOST_USE_WINDOWS_H )
const DWORD_ DONT_RESOLVE_DLL_REFERENCES_           = 0x00000001;
const DWORD_ LOAD_WITH_ALTERED_SEARCH_PATH_         = 0x00000008;
#endif // defined( BOOST_USE_WINDOWS_H )

// This one is not defined by MinGW
const DWORD_ LOAD_IGNORE_CODE_AUTHZ_LEVEL_          = 0x00000010;
=======
BOOST_CONSTEXPR_OR_CONST DWORD_ DONT_RESOLVE_DLL_REFERENCES_           = DONT_RESOLVE_DLL_REFERENCES;
BOOST_CONSTEXPR_OR_CONST DWORD_ LOAD_WITH_ALTERED_SEARCH_PATH_         = LOAD_WITH_ALTERED_SEARCH_PATH;
#else // defined( BOOST_USE_WINDOWS_H )
BOOST_CONSTEXPR_OR_CONST DWORD_ DONT_RESOLVE_DLL_REFERENCES_           = 0x00000001;
BOOST_CONSTEXPR_OR_CONST DWORD_ LOAD_WITH_ALTERED_SEARCH_PATH_         = 0x00000008;
#endif // defined( BOOST_USE_WINDOWS_H )

// This one is not defined by MinGW
BOOST_CONSTEXPR_OR_CONST DWORD_ LOAD_IGNORE_CODE_AUTHZ_LEVEL_          = 0x00000010;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#if !defined( BOOST_NO_ANSI_APIS )
using ::LoadLibraryA;
using ::LoadLibraryExA;
using ::GetModuleHandleA;
using ::GetModuleFileNameA;
#endif // !defined( BOOST_NO_ANSI_APIS )
using ::LoadLibraryW;
using ::LoadLibraryExW;
using ::GetModuleHandleW;
using ::GetModuleFileNameW;

#if !defined( UNDER_CE )
// For backward compatibility, don't use directly. Use get_proc_address instead.
using ::GetProcAddress;
#else
using ::GetProcAddressA;
using ::GetProcAddressW;
#endif

BOOST_FORCEINLINE FARPROC_ get_proc_address(HMODULE_ hModule, LPCSTR_ lpProcName)
{
#if !defined( UNDER_CE )
    return ::GetProcAddress(hModule, lpProcName);
#else
    return ::GetProcAddressA(hModule, lpProcName);
#endif
}

<<<<<<< HEAD
BOOST_FORCEINLINE SIZE_T_ VirtualQuery(LPCVOID_ lpAddress, MEMORY_BASIC_INFORMATION_* lpBuffer, ULONG_PTR_ dwLength)
=======
BOOST_FORCEINLINE SIZE_T_ VirtualQuery(LPCVOID_ lpAddress, MEMORY_BASIC_INFORMATION_* lpBuffer, SIZE_T_ dwLength)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    return ::VirtualQuery(lpAddress, reinterpret_cast< ::_MEMORY_BASIC_INFORMATION* >(lpBuffer), dwLength);
}

#if !defined( BOOST_NO_ANSI_APIS )
BOOST_FORCEINLINE HMODULE_ load_library(LPCSTR_ lpFileName)
{
    return ::LoadLibraryA(lpFileName);
}

BOOST_FORCEINLINE HMODULE_ load_library_ex(LPCSTR_ lpFileName, HANDLE_ hFile, DWORD_ dwFlags)
{
    return ::LoadLibraryExA(lpFileName, hFile, dwFlags);
}

BOOST_FORCEINLINE HMODULE_ get_module_handle(LPCSTR_ lpFileName)
{
    return ::GetModuleHandleA(lpFileName);
}

BOOST_FORCEINLINE DWORD_ get_module_file_name(HMODULE_ hModule, LPSTR_ lpFilename, DWORD_ nSize)
{
    return ::GetModuleFileNameA(hModule, lpFilename, nSize);
}
#endif // #if !defined( BOOST_NO_ANSI_APIS )

BOOST_FORCEINLINE HMODULE_ load_library(LPCWSTR_ lpFileName)
{
    return ::LoadLibraryW(lpFileName);
}

BOOST_FORCEINLINE HMODULE_ load_library_ex(LPCWSTR_ lpFileName, HANDLE_ hFile, DWORD_ dwFlags)
{
    return ::LoadLibraryExW(lpFileName, hFile, dwFlags);
}

BOOST_FORCEINLINE HMODULE_ get_module_handle(LPCWSTR_ lpFileName)
{
    return ::GetModuleHandleW(lpFileName);
}

BOOST_FORCEINLINE DWORD_ get_module_file_name(HMODULE_ hModule, LPWSTR_ lpFilename, DWORD_ nSize)
{
    return ::GetModuleFileNameW(hModule, lpFilename, nSize);
}

} // namespace winapi
} // namespace boost

#endif // BOOST_WINAPI_PARTITION_DESKTOP || BOOST_WINAPI_PARTITION_SYSTEM

//
// FreeLibrary is in a different partition set (slightly)
//

#if BOOST_WINAPI_PARTITION_APP || BOOST_WINAPI_PARTITION_SYSTEM

#if !defined(BOOST_USE_WINDOWS_H)
extern "C" {
<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ WINAPI
=======
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ BOOST_WINAPI_WINAPI_CC
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
FreeLibrary(boost::winapi::HMODULE_ hModule);
}
#endif

namespace boost {
namespace winapi {
using ::FreeLibrary;
}
}

#endif // BOOST_WINAPI_PARTITION_APP || BOOST_WINAPI_PARTITION_SYSTEM
#endif // BOOST_WINAPI_DLL_HPP_INCLUDED_
