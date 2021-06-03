//
//  boost/signals2/detail/lwm_win32_cs.hpp
//
//  Copyright (c) 2002, 2003 Peter Dimov
//  Copyright (c) 2008 Frank Mori Hess
//  Copyright (c) Microsoft Corporation 2014
//
// Distributed under the Boost Software License, Version 1.0. (See
// accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)
//

#ifndef BOOST_SIGNALS2_LWM_WIN32_CS_HPP
#define BOOST_SIGNALS2_LWM_WIN32_CS_HPP

// MS compatible compilers support #pragma once

<<<<<<< HEAD
#if defined(_MSC_VER)
# pragma once
#endif

#include <boost/assert.hpp>

#ifdef BOOST_USE_WINDOWS_H
#  include <windows.h>
#endif

#include <boost/predef/platform.h>
=======
#if defined(_MSC_VER) && (_MSC_VER >= 1020)
# pragma once
#endif

#include <boost/predef.h>
#include <boost/assert.hpp>

#ifdef BOOST_USE_WINDOWS_H

#include <windows.h>

#else

struct _RTL_CRITICAL_SECTION;

#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost
{

namespace signals2
{

<<<<<<< HEAD
=======
namespace detail
{

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#ifndef BOOST_USE_WINDOWS_H

struct critical_section
{
    struct critical_section_debug * DebugInfo;
    long LockCount;
    long RecursionCount;
    void * OwningThread;
    void * LockSemaphore;
#if defined(_WIN64)
    unsigned __int64 SpinCount;
#else
    unsigned long SpinCount;
#endif
};

#if BOOST_PLAT_WINDOWS_RUNTIME
<<<<<<< HEAD
extern "C" __declspec(dllimport) void __stdcall InitializeCriticalSectionEx(critical_section *, unsigned long, unsigned long);
#else
extern "C" __declspec(dllimport) void __stdcall InitializeCriticalSection(critical_section *);
#endif
extern "C" __declspec(dllimport) void __stdcall EnterCriticalSection(critical_section *);
extern "C" __declspec(dllimport) int __stdcall TryEnterCriticalSection(critical_section *);
extern "C" __declspec(dllimport) void __stdcall LeaveCriticalSection(critical_section *);
extern "C" __declspec(dllimport) void __stdcall DeleteCriticalSection(critical_section *);

#else

typedef ::CRITICAL_SECTION critical_section;

#endif // #ifndef BOOST_USE_WINDOWS_H

=======
extern "C" __declspec(dllimport) void __stdcall InitializeCriticalSectionEx(::_RTL_CRITICAL_SECTION *, unsigned long, unsigned long);
#else
extern "C" __declspec(dllimport) void __stdcall InitializeCriticalSection(::_RTL_CRITICAL_SECTION *);
#endif
extern "C" __declspec(dllimport) void __stdcall EnterCriticalSection(::_RTL_CRITICAL_SECTION *);
extern "C" __declspec(dllimport) int __stdcall TryEnterCriticalSection(::_RTL_CRITICAL_SECTION *);
extern "C" __declspec(dllimport) void __stdcall LeaveCriticalSection(::_RTL_CRITICAL_SECTION *);
extern "C" __declspec(dllimport) void __stdcall DeleteCriticalSection(::_RTL_CRITICAL_SECTION *);

typedef ::_RTL_CRITICAL_SECTION rtl_critical_section;

#else // #ifndef BOOST_USE_WINDOWS_H

typedef ::CRITICAL_SECTION critical_section;

#if BOOST_PLAT_WINDOWS_RUNTIME
using ::InitializeCriticalSectionEx;
#else
using ::InitializeCriticalSection;
#endif
using ::EnterCriticalSection;
using ::TryEnterCriticalSection;
using ::LeaveCriticalSection;
using ::DeleteCriticalSection;

typedef ::CRITICAL_SECTION rtl_critical_section;

#endif // #ifndef BOOST_USE_WINDOWS_H

} // namespace detail

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
class mutex
{
private:

<<<<<<< HEAD
    critical_section cs_;
=======
    boost::signals2::detail::critical_section cs_;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

    mutex(mutex const &);
    mutex & operator=(mutex const &);

public:

    mutex()
    {
#if BOOST_PLAT_WINDOWS_RUNTIME
<<<<<<< HEAD
        InitializeCriticalSectionEx(&cs_, 4000, 0);
#else
        InitializeCriticalSection(&cs_);
=======
        boost::signals2::detail::InitializeCriticalSectionEx(reinterpret_cast< boost::signals2::detail::rtl_critical_section* >(&cs_), 4000, 0);
#else
        boost::signals2::detail::InitializeCriticalSection(reinterpret_cast< boost::signals2::detail::rtl_critical_section* >(&cs_)); 
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#endif
    }

    ~mutex()
    {
<<<<<<< HEAD
        DeleteCriticalSection(&cs_);
=======
        boost::signals2::detail::DeleteCriticalSection(reinterpret_cast< boost::signals2::detail::rtl_critical_section* >(&cs_)); 
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }

    void lock()
    {
<<<<<<< HEAD
        EnterCriticalSection(&cs_);
=======
        boost::signals2::detail::EnterCriticalSection(reinterpret_cast< boost::signals2::detail::rtl_critical_section* >(&cs_)); 
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
// TryEnterCriticalSection only exists on Windows NT 4.0 and later
#if (defined(_WIN32_WINNT) && (_WIN32_WINNT >= 0x0400))
    bool try_lock()
    {
<<<<<<< HEAD
        return TryEnterCriticalSection(&cs_) != 0;
=======
        return boost::signals2::detail::TryEnterCriticalSection(reinterpret_cast< boost::signals2::detail::rtl_critical_section* >(&cs_)) != 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
#else
    bool try_lock()
    {
        BOOST_ASSERT(false);
        return false;
    }
#endif
    void unlock()
    {
<<<<<<< HEAD
        LeaveCriticalSection(&cs_);
=======
        boost::signals2::detail::LeaveCriticalSection(reinterpret_cast< boost::signals2::detail::rtl_critical_section* >(&cs_));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
};

} // namespace signals2

} // namespace boost

#endif // #ifndef BOOST_SIGNALS2_LWM_WIN32_CS_HPP
