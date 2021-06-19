/*
 * Copyright 2016 Klemens D. Morgenstern
 * Copyright 2016, 2017 Andrey Semashev
 *
 * Distributed under the Boost Software License, Version 1.0.
 * See http://www.boost.org/LICENSE_1_0.txt
 */

#ifndef BOOST_WINAPI_PIPES_HPP_INCLUDED_
#define BOOST_WINAPI_PIPES_HPP_INCLUDED_

#include <boost/winapi/basic_types.hpp>
#include <boost/winapi/config.hpp>
#include <boost/winapi/overlapped.hpp>

#ifdef BOOST_HAS_PRAGMA_ONCE
#pragma once
#endif

#if BOOST_WINAPI_PARTITION_DESKTOP_SYSTEM

#if !defined( BOOST_USE_WINDOWS_H ) && BOOST_WINAPI_PARTITION_DESKTOP && !defined( BOOST_NO_ANSI_APIS )
extern "C" {
<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::HANDLE_ WINAPI CreateNamedPipeA(
=======
BOOST_SYMBOL_IMPORT boost::winapi::HANDLE_ BOOST_WINAPI_WINAPI_CC CreateNamedPipeA(
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::winapi::LPCSTR_ lpName,
    boost::winapi::DWORD_ dwOpenMode,
    boost::winapi::DWORD_ dwPipeMode,
    boost::winapi::DWORD_ nMaxInstances,
    boost::winapi::DWORD_ nOutBufferSize,
    boost::winapi::DWORD_ nInBufferSize,
    boost::winapi::DWORD_ nDefaultTimeOut,
    _SECURITY_ATTRIBUTES *lpSecurityAttributes);
} // extern "C"
#endif // !defined( BOOST_USE_WINDOWS_H ) && BOOST_WINAPI_PARTITION_DESKTOP && !defined( BOOST_NO_ANSI_APIS )

#if !defined( BOOST_USE_WINDOWS_H )
extern "C" {

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ WINAPI ImpersonateNamedPipeClient(
    boost::winapi::HANDLE_ hNamedPipe);

BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ WINAPI CreatePipe(
=======
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ BOOST_WINAPI_WINAPI_CC ImpersonateNamedPipeClient(
    boost::winapi::HANDLE_ hNamedPipe);

BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ BOOST_WINAPI_WINAPI_CC CreatePipe(
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::winapi::PHANDLE_ hReadPipe,
    boost::winapi::PHANDLE_ hWritePipe,
    _SECURITY_ATTRIBUTES* lpPipeAttributes,
    boost::winapi::DWORD_ nSize);

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ WINAPI ConnectNamedPipe(
    boost::winapi::HANDLE_ hNamedPipe,
    _OVERLAPPED* lpOverlapped);

BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ WINAPI DisconnectNamedPipe(
    boost::winapi::HANDLE_ hNamedPipe);

BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ WINAPI SetNamedPipeHandleState(
=======
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ BOOST_WINAPI_WINAPI_CC ConnectNamedPipe(
    boost::winapi::HANDLE_ hNamedPipe,
    _OVERLAPPED* lpOverlapped);

BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ BOOST_WINAPI_WINAPI_CC DisconnectNamedPipe(
    boost::winapi::HANDLE_ hNamedPipe);

BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ BOOST_WINAPI_WINAPI_CC SetNamedPipeHandleState(
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::winapi::HANDLE_ hNamedPipe,
    boost::winapi::LPDWORD_ lpMode,
    boost::winapi::LPDWORD_ lpMaxCollectionCount,
    boost::winapi::LPDWORD_ lpCollectDataTimeout);

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ WINAPI PeekNamedPipe(
=======
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ BOOST_WINAPI_WINAPI_CC PeekNamedPipe(
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::winapi::HANDLE_ hNamedPipe,
    boost::winapi::LPVOID_ lpBuffer,
    boost::winapi::DWORD_ nBufferSize,
    boost::winapi::LPDWORD_ lpBytesRead,
    boost::winapi::LPDWORD_ lpTotalBytesAvail,
    boost::winapi::LPDWORD_ lpBytesLeftThisMessage);

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ WINAPI TransactNamedPipe(
=======
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ BOOST_WINAPI_WINAPI_CC TransactNamedPipe(
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::winapi::HANDLE_ hNamedPipe,
    boost::winapi::LPVOID_ lpInBuffer,
    boost::winapi::DWORD_ nInBufferSize,
    boost::winapi::LPVOID_ lpOutBuffer,
    boost::winapi::DWORD_ nOutBufferSize,
    boost::winapi::LPDWORD_ lpBytesRead,
    _OVERLAPPED* lpOverlapped);

#if !defined( BOOST_NO_ANSI_APIS )
<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ WINAPI WaitNamedPipeA(
=======
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ BOOST_WINAPI_WINAPI_CC WaitNamedPipeA(
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::winapi::LPCSTR_ lpNamedPipeName,
    boost::winapi::DWORD_ nTimeOut);
#endif // !defined( BOOST_NO_ANSI_APIS )

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::HANDLE_ WINAPI CreateNamedPipeW(
=======
BOOST_SYMBOL_IMPORT boost::winapi::HANDLE_ BOOST_WINAPI_WINAPI_CC CreateNamedPipeW(
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::winapi::LPCWSTR_ lpName,
    boost::winapi::DWORD_ dwOpenMode,
    boost::winapi::DWORD_ dwPipeMode,
    boost::winapi::DWORD_ nMaxInstances,
    boost::winapi::DWORD_ nOutBufferSize,
    boost::winapi::DWORD_ nInBufferSize,
    boost::winapi::DWORD_ nDefaultTimeOut,
    _SECURITY_ATTRIBUTES* lpSecurityAttributes);

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ WINAPI WaitNamedPipeW(
=======
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ BOOST_WINAPI_WINAPI_CC WaitNamedPipeW(
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::winapi::LPCWSTR_ lpNamedPipeName,
    boost::winapi::DWORD_ nTimeOut);

#if BOOST_USE_WINAPI_VERSION >= BOOST_WINAPI_VERSION_WIN6
#if !defined( BOOST_NO_ANSI_APIS )
<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ WINAPI GetNamedPipeClientComputerNameA(
=======
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ BOOST_WINAPI_WINAPI_CC GetNamedPipeClientComputerNameA(
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::winapi::HANDLE_ Pipe,
    boost::winapi::LPSTR_ ClientComputerName,
    boost::winapi::ULONG_ ClientComputerNameLength);
#endif // !defined( BOOST_NO_ANSI_APIS )

<<<<<<< HEAD
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ WINAPI GetNamedPipeClientComputerNameW(
=======
BOOST_SYMBOL_IMPORT boost::winapi::BOOL_ BOOST_WINAPI_WINAPI_CC GetNamedPipeClientComputerNameW(
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    boost::winapi::HANDLE_ Pipe,
    boost::winapi::LPWSTR_ ClientComputerName,
    boost::winapi::ULONG_ ClientComputerNameLength);
#endif

} // extern "C"
#endif // !defined( BOOST_USE_WINDOWS_H )

namespace boost {
namespace winapi {

#if defined( BOOST_USE_WINDOWS_H )

<<<<<<< HEAD
const DWORD_ PIPE_ACCESS_DUPLEX_ = PIPE_ACCESS_DUPLEX;
const DWORD_ PIPE_ACCESS_INBOUND_ = PIPE_ACCESS_INBOUND;
const DWORD_ PIPE_ACCESS_OUTBOUND_ = PIPE_ACCESS_OUTBOUND;

const DWORD_ PIPE_TYPE_BYTE_ = PIPE_TYPE_BYTE;
const DWORD_ PIPE_TYPE_MESSAGE_ = PIPE_TYPE_MESSAGE;

const DWORD_ PIPE_READMODE_BYTE_ = PIPE_READMODE_BYTE;
const DWORD_ PIPE_READMODE_MESSAGE_ = PIPE_READMODE_MESSAGE;

const DWORD_ PIPE_WAIT_ = PIPE_WAIT;
const DWORD_ PIPE_NOWAIT_ = PIPE_NOWAIT;

const DWORD_ PIPE_UNLIMITED_INSTANCES_ = PIPE_UNLIMITED_INSTANCES;

const DWORD_ NMPWAIT_USE_DEFAULT_WAIT_ = NMPWAIT_USE_DEFAULT_WAIT;
const DWORD_ NMPWAIT_NOWAIT_ = NMPWAIT_NOWAIT;
const DWORD_ NMPWAIT_WAIT_FOREVER_ = NMPWAIT_WAIT_FOREVER;

#else // defined( BOOST_USE_WINDOWS_H )

const DWORD_ PIPE_ACCESS_DUPLEX_ = 0x00000003;
const DWORD_ PIPE_ACCESS_INBOUND_ = 0x00000001;
const DWORD_ PIPE_ACCESS_OUTBOUND_ = 0x00000002;

const DWORD_ PIPE_TYPE_BYTE_ = 0x00000000;
const DWORD_ PIPE_TYPE_MESSAGE_ = 0x00000004;

const DWORD_ PIPE_READMODE_BYTE_ = 0x00000000;
const DWORD_ PIPE_READMODE_MESSAGE_ = 0x00000002;

const DWORD_ PIPE_WAIT_ = 0x00000000;
const DWORD_ PIPE_NOWAIT_ = 0x00000001;

const DWORD_ PIPE_UNLIMITED_INSTANCES_ = 255u;

const DWORD_ NMPWAIT_USE_DEFAULT_WAIT_ = 0x00000000;
const DWORD_ NMPWAIT_NOWAIT_ = 0x00000001;
const DWORD_ NMPWAIT_WAIT_FOREVER_ = 0xFFFFFFFF;
=======
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_ACCESS_DUPLEX_ = PIPE_ACCESS_DUPLEX;
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_ACCESS_INBOUND_ = PIPE_ACCESS_INBOUND;
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_ACCESS_OUTBOUND_ = PIPE_ACCESS_OUTBOUND;

BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_TYPE_BYTE_ = PIPE_TYPE_BYTE;
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_TYPE_MESSAGE_ = PIPE_TYPE_MESSAGE;

BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_READMODE_BYTE_ = PIPE_READMODE_BYTE;
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_READMODE_MESSAGE_ = PIPE_READMODE_MESSAGE;

BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_WAIT_ = PIPE_WAIT;
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_NOWAIT_ = PIPE_NOWAIT;

BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_UNLIMITED_INSTANCES_ = PIPE_UNLIMITED_INSTANCES;

BOOST_CONSTEXPR_OR_CONST DWORD_ NMPWAIT_USE_DEFAULT_WAIT_ = NMPWAIT_USE_DEFAULT_WAIT;
BOOST_CONSTEXPR_OR_CONST DWORD_ NMPWAIT_NOWAIT_ = NMPWAIT_NOWAIT;
BOOST_CONSTEXPR_OR_CONST DWORD_ NMPWAIT_WAIT_FOREVER_ = NMPWAIT_WAIT_FOREVER;

#else // defined( BOOST_USE_WINDOWS_H )

BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_ACCESS_DUPLEX_ = 0x00000003;
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_ACCESS_INBOUND_ = 0x00000001;
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_ACCESS_OUTBOUND_ = 0x00000002;

BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_TYPE_BYTE_ = 0x00000000;
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_TYPE_MESSAGE_ = 0x00000004;

BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_READMODE_BYTE_ = 0x00000000;
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_READMODE_MESSAGE_ = 0x00000002;

BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_WAIT_ = 0x00000000;
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_NOWAIT_ = 0x00000001;

BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_UNLIMITED_INSTANCES_ = 255u;

BOOST_CONSTEXPR_OR_CONST DWORD_ NMPWAIT_USE_DEFAULT_WAIT_ = 0x00000000;
BOOST_CONSTEXPR_OR_CONST DWORD_ NMPWAIT_NOWAIT_ = 0x00000001;
BOOST_CONSTEXPR_OR_CONST DWORD_ NMPWAIT_WAIT_FOREVER_ = 0xFFFFFFFF;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#endif // defined( BOOST_USE_WINDOWS_H )

// These constants are not defined in Windows SDK prior to 7.0A
<<<<<<< HEAD
const DWORD_ PIPE_ACCEPT_REMOTE_CLIENTS_ = 0x00000000;
const DWORD_ PIPE_REJECT_REMOTE_CLIENTS_ = 0x00000008;
=======
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_ACCEPT_REMOTE_CLIENTS_ = 0x00000000;
BOOST_CONSTEXPR_OR_CONST DWORD_ PIPE_REJECT_REMOTE_CLIENTS_ = 0x00000008;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

using ::ImpersonateNamedPipeClient;
using ::DisconnectNamedPipe;
using ::SetNamedPipeHandleState;
using ::PeekNamedPipe;

#if !defined( BOOST_NO_ANSI_APIS )
using ::WaitNamedPipeA;
#endif
using ::WaitNamedPipeW;

#if BOOST_USE_WINAPI_VERSION >= BOOST_WINAPI_VERSION_WIN6
#if !defined( BOOST_NO_ANSI_APIS )
using ::GetNamedPipeClientComputerNameA;
#endif // !defined( BOOST_NO_ANSI_APIS )
using ::GetNamedPipeClientComputerNameW;
#endif // BOOST_USE_WINAPI_VERSION >= BOOST_WINAPI_VERSION_WIN6

BOOST_FORCEINLINE BOOL_ CreatePipe(PHANDLE_ hReadPipe, PHANDLE_ hWritePipe, LPSECURITY_ATTRIBUTES_ lpPipeAttributes, DWORD_ nSize)
{
    return ::CreatePipe(hReadPipe, hWritePipe, reinterpret_cast< ::_SECURITY_ATTRIBUTES* >(lpPipeAttributes), nSize);
}

BOOST_FORCEINLINE BOOL_ ConnectNamedPipe(HANDLE_ hNamedPipe, LPOVERLAPPED_ lpOverlapped)
{
    return ::ConnectNamedPipe(hNamedPipe, reinterpret_cast< ::_OVERLAPPED* >(lpOverlapped));
}

BOOST_FORCEINLINE BOOL_ TransactNamedPipe(HANDLE_ hNamedPipe, LPVOID_ lpInBuffer, DWORD_ nInBufferSize, LPVOID_ lpOutBuffer, DWORD_ nOutBufferSize, LPDWORD_ lpBytesRead, LPOVERLAPPED_ lpOverlapped)
{
    return ::TransactNamedPipe(hNamedPipe, lpInBuffer, nInBufferSize, lpOutBuffer, nOutBufferSize, lpBytesRead, reinterpret_cast< ::_OVERLAPPED* >(lpOverlapped));
}


#if BOOST_WINAPI_PARTITION_DESKTOP && !defined( BOOST_NO_ANSI_APIS )
BOOST_FORCEINLINE HANDLE_ CreateNamedPipeA(
    LPCSTR_ lpName,
    DWORD_ dwOpenMode,
    DWORD_ dwPipeMode,
    DWORD_ nMaxInstances,
    DWORD_ nOutBufferSize,
    DWORD_ nInBufferSize,
    DWORD_ nDefaultTimeOut,
    LPSECURITY_ATTRIBUTES_ lpSecurityAttributes)
{
    return ::CreateNamedPipeA(
        lpName,
        dwOpenMode,
        dwPipeMode,
        nMaxInstances,
        nOutBufferSize,
        nInBufferSize,
        nDefaultTimeOut,
        reinterpret_cast< ::_SECURITY_ATTRIBUTES* >(lpSecurityAttributes));
}

BOOST_FORCEINLINE HANDLE_ create_named_pipe(
    LPCSTR_ lpName,
    DWORD_ dwOpenMode,
    DWORD_ dwPipeMode,
    DWORD_ nMaxInstances,
    DWORD_ nOutBufferSize,
    DWORD_ nInBufferSize,
    DWORD_ nDefaultTimeOut,
    LPSECURITY_ATTRIBUTES_ lpSecurityAttributes)
{
    return ::CreateNamedPipeA(
        lpName,
        dwOpenMode,
        dwPipeMode,
        nMaxInstances,
        nOutBufferSize,
        nInBufferSize,
        nDefaultTimeOut,
        reinterpret_cast< ::_SECURITY_ATTRIBUTES* >(lpSecurityAttributes));
}
#endif // BOOST_WINAPI_PARTITION_DESKTOP && !defined( BOOST_NO_ANSI_APIS )

BOOST_FORCEINLINE HANDLE_ CreateNamedPipeW(
    LPCWSTR_ lpName,
    DWORD_ dwOpenMode,
    DWORD_ dwPipeMode,
    DWORD_ nMaxInstances,
    DWORD_ nOutBufferSize,
    DWORD_ nInBufferSize,
    DWORD_ nDefaultTimeOut,
    LPSECURITY_ATTRIBUTES_ lpSecurityAttributes)
{
    return ::CreateNamedPipeW(
        lpName,
        dwOpenMode,
        dwPipeMode,
        nMaxInstances,
        nOutBufferSize,
        nInBufferSize,
        nDefaultTimeOut,
        reinterpret_cast< ::_SECURITY_ATTRIBUTES* >(lpSecurityAttributes));
}

BOOST_FORCEINLINE HANDLE_ create_named_pipe(
    LPCWSTR_ lpName,
    DWORD_ dwOpenMode,
    DWORD_ dwPipeMode,
    DWORD_ nMaxInstances,
    DWORD_ nOutBufferSize,
    DWORD_ nInBufferSize,
    DWORD_ nDefaultTimeOut,
    LPSECURITY_ATTRIBUTES_ lpSecurityAttributes)
{
    return ::CreateNamedPipeW(
        lpName,
        dwOpenMode,
        dwPipeMode,
        nMaxInstances,
        nOutBufferSize,
        nInBufferSize,
        nDefaultTimeOut,
        reinterpret_cast< ::_SECURITY_ATTRIBUTES* >(lpSecurityAttributes));
}

#if !defined( BOOST_NO_ANSI_APIS )
BOOST_FORCEINLINE BOOL_ wait_named_pipe(LPCSTR_ lpNamedPipeName, DWORD_ nTimeOut)
{
    return ::WaitNamedPipeA(lpNamedPipeName, nTimeOut);
}
#endif //BOOST_NO_ANSI_APIS

BOOST_FORCEINLINE BOOL_ wait_named_pipe(LPCWSTR_ lpNamedPipeName, DWORD_ nTimeOut)
{
    return ::WaitNamedPipeW(lpNamedPipeName, nTimeOut);
}

#if BOOST_USE_WINAPI_VERSION >= BOOST_WINAPI_VERSION_WIN6

#if !defined( BOOST_NO_ANSI_APIS )
BOOST_FORCEINLINE BOOL_ get_named_pipe_client_computer_name(HANDLE_ Pipe, LPSTR_ ClientComputerName, ULONG_ ClientComputerNameLength)
{
    return ::GetNamedPipeClientComputerNameA(Pipe, ClientComputerName, ClientComputerNameLength);
}
#endif // !defined( BOOST_NO_ANSI_APIS )

BOOST_FORCEINLINE BOOL_ get_named_pipe_client_computer_name(HANDLE_ Pipe, LPWSTR_ ClientComputerName, ULONG_ ClientComputerNameLength)
{
    return ::GetNamedPipeClientComputerNameW(Pipe, ClientComputerName, ClientComputerNameLength);
}

#endif // BOOST_USE_WINAPI_VERSION >= BOOST_WINAPI_VERSION_WIN6

}
}

#endif // BOOST_WINAPI_PARTITION_DESKTOP_SYSTEM

#endif // BOOST_WINAPI_PIPES_HPP_INCLUDED_
