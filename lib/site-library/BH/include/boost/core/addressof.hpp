/*
Copyright (C) 2002 Brad King (brad.king@kitware.com)
                   Douglas Gregor (gregod@cs.rpi.edu)

Copyright (C) 2002, 2008, 2013 Peter Dimov

Copyright (C) 2017 Glen Joseph Fernandes (glenjofe@gmail.com)

Distributed under the Boost Software License, Version 1.0.
(See accompanying file LICENSE_1_0.txt or copy at
http://www.boost.org/LICENSE_1_0.txt)
*/

#ifndef BOOST_CORE_ADDRESSOF_HPP
#define BOOST_CORE_ADDRESSOF_HPP

#include <boost/config.hpp>

#if defined(BOOST_MSVC_FULL_VER) && BOOST_MSVC_FULL_VER >= 190024215
#define BOOST_CORE_HAS_BUILTIN_ADDRESSOF
#elif defined(BOOST_GCC) && BOOST_GCC >= 70000
#define BOOST_CORE_HAS_BUILTIN_ADDRESSOF
#elif defined(__has_builtin)
#if __has_builtin(__builtin_addressof)
#define BOOST_CORE_HAS_BUILTIN_ADDRESSOF
#endif
#endif

#if defined(BOOST_CORE_HAS_BUILTIN_ADDRESSOF)
#if defined(BOOST_NO_CXX11_CONSTEXPR)
#define BOOST_CORE_NO_CONSTEXPR_ADDRESSOF
#endif

namespace boost {

template<class T>
BOOST_CONSTEXPR inline T*
addressof(T& o) BOOST_NOEXCEPT
{
    return __builtin_addressof(o);
}

} /* boost */
#else
#include <boost/config/workaround.hpp>
#include <cstddef>

namespace boost {
namespace detail {

template<class T>
<<<<<<< HEAD
class addressof_ref {
public:
    BOOST_FORCEINLINE addressof_ref(T& o) BOOST_NOEXCEPT
=======
class addrof_ref {
public:
    BOOST_FORCEINLINE addrof_ref(T& o) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        : o_(o) { }
    BOOST_FORCEINLINE operator T&() const BOOST_NOEXCEPT {
        return o_;
    }
private:
<<<<<<< HEAD
    addressof_ref& operator=(const addressof_ref&);
=======
    addrof_ref& operator=(const addrof_ref&);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    T& o_;
};

template<class T>
<<<<<<< HEAD
struct address_of {
=======
struct addrof {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static BOOST_FORCEINLINE T* get(T& o, long) BOOST_NOEXCEPT {
        return reinterpret_cast<T*>(&
            const_cast<char&>(reinterpret_cast<const volatile char&>(o)));
    }
    static BOOST_FORCEINLINE T* get(T* p, int) BOOST_NOEXCEPT {
        return p;
    }
};

#if !defined(BOOST_NO_CXX11_NULLPTR)
#if !defined(BOOST_NO_CXX11_DECLTYPE) && \
    (defined(__INTEL_COMPILER) || \
        (defined(__clang__) && !defined(_LIBCPP_VERSION)))
<<<<<<< HEAD
typedef decltype(nullptr) addressof_null_t;
#else
typedef std::nullptr_t addressof_null_t;
#endif

template<>
struct address_of<addressof_null_t> {
    typedef addressof_null_t type;
=======
typedef decltype(nullptr) addrof_null_t;
#else
typedef std::nullptr_t addrof_null_t;
#endif

template<>
struct addrof<addrof_null_t> {
    typedef addrof_null_t type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static BOOST_FORCEINLINE type* get(type& o, int) BOOST_NOEXCEPT {
        return &o;
    }
};

template<>
<<<<<<< HEAD
struct address_of<const addressof_null_t> {
    typedef const addressof_null_t type;
=======
struct addrof<const addrof_null_t> {
    typedef const addrof_null_t type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static BOOST_FORCEINLINE type* get(type& o, int) BOOST_NOEXCEPT {
        return &o;
    }
};

template<>
<<<<<<< HEAD
struct address_of<volatile addressof_null_t> {
    typedef volatile addressof_null_t type;
=======
struct addrof<volatile addrof_null_t> {
    typedef volatile addrof_null_t type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static BOOST_FORCEINLINE type* get(type& o, int) BOOST_NOEXCEPT {
        return &o;
    }
};

template<>
<<<<<<< HEAD
struct address_of<const volatile addressof_null_t> {
    typedef const volatile addressof_null_t type;
=======
struct addrof<const volatile addrof_null_t> {
    typedef const volatile addrof_null_t type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static BOOST_FORCEINLINE type* get(type& o, int) BOOST_NOEXCEPT {
        return &o;
    }
};
#endif

} /* detail */

#if defined(BOOST_NO_CXX11_SFINAE_EXPR) || \
    defined(BOOST_NO_CXX11_CONSTEXPR) || \
    defined(BOOST_NO_CXX11_DECLTYPE)
#define BOOST_CORE_NO_CONSTEXPR_ADDRESSOF

template<class T>
BOOST_FORCEINLINE T*
addressof(T& o) BOOST_NOEXCEPT
{
#if BOOST_WORKAROUND(__BORLANDC__, BOOST_TESTED_AT(0x610)) || \
    BOOST_WORKAROUND(__SUNPRO_CC, <= 0x5120)
<<<<<<< HEAD
    return detail::address_of<T>::get(o, 0);
#else
    return detail::address_of<T>::get(detail::addressof_ref<T>(o), 0);
=======
    return boost::detail::addrof<T>::get(o, 0);
#else
    return boost::detail::addrof<T>::get(boost::detail::addrof_ref<T>(o), 0);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#endif
}

#if BOOST_WORKAROUND(__SUNPRO_CC, BOOST_TESTED_AT(0x590))
namespace detail {

template<class T>
<<<<<<< HEAD
struct addressof_result {
=======
struct addrof_result {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef T* type;
};

} /* detail */

template<class T, std::size_t N>
<<<<<<< HEAD
BOOST_FORCEINLINE typename detail::addressof_result<T[N]>::type
=======
BOOST_FORCEINLINE typename boost::detail::addrof_result<T[N]>::type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
addressof(T (&o)[N]) BOOST_NOEXCEPT
{
    return &o;
}
#endif

#if BOOST_WORKAROUND(__BORLANDC__, BOOST_TESTED_AT(0x564))
template<class T, std::size_t N>
BOOST_FORCEINLINE
T (*addressof(T (&o)[N]) BOOST_NOEXCEPT)[N]
{
   return reinterpret_cast<T(*)[N]>(&o);
}

template<class T, std::size_t N>
BOOST_FORCEINLINE
const T (*addressof(const T (&o)[N]) BOOST_NOEXCEPT)[N]
{
   return reinterpret_cast<const T(*)[N]>(&o);
}
#endif
#else
namespace detail {

template<class T>
<<<<<<< HEAD
T addressof_declval() BOOST_NOEXCEPT;

template<class>
struct addressof_void {
=======
T addrof_declval() BOOST_NOEXCEPT;

template<class>
struct addrof_void {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef void type;
};

template<class T, class E = void>
<<<<<<< HEAD
struct addressof_member_operator {
=======
struct addrof_member_operator {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static constexpr bool value = false;
};

template<class T>
<<<<<<< HEAD
struct addressof_member_operator<T, typename
    addressof_void<decltype(addressof_declval<T&>().operator&())>::type> {
=======
struct addrof_member_operator<T, typename
    addrof_void<decltype(addrof_declval<T&>().operator&())>::type> {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static constexpr bool value = true;
};

#if BOOST_WORKAROUND(BOOST_INTEL, < 1600)
<<<<<<< HEAD
struct addressof_addressable { };

addressof_addressable*
operator&(addressof_addressable&) BOOST_NOEXCEPT;
#endif

template<class T, class E = void>
struct addressof_non_member_operator {
=======
struct addrof_addressable { };

addrof_addressable*
operator&(addrof_addressable&) BOOST_NOEXCEPT;
#endif

template<class T, class E = void>
struct addrof_non_member_operator {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static constexpr bool value = false;
};

template<class T>
<<<<<<< HEAD
struct addressof_non_member_operator<T, typename
    addressof_void<decltype(operator&(addressof_declval<T&>()))>::type> {
=======
struct addrof_non_member_operator<T, typename
    addrof_void<decltype(operator&(addrof_declval<T&>()))>::type> {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static constexpr bool value = true;
};

template<class T, class E = void>
<<<<<<< HEAD
struct addressof_expression {
=======
struct addrof_expression {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static constexpr bool value = false;
};

template<class T>
<<<<<<< HEAD
struct addressof_expression<T,
    typename addressof_void<decltype(&addressof_declval<T&>())>::type> {
=======
struct addrof_expression<T,
    typename addrof_void<decltype(&addrof_declval<T&>())>::type> {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    static constexpr bool value = true;
};

template<class T>
<<<<<<< HEAD
struct addressof_is_constexpr {
    static constexpr bool value = addressof_expression<T>::value &&
        !addressof_member_operator<T>::value &&
        !addressof_non_member_operator<T>::value;
};

template<bool E, class T>
struct addressof_if { };

template<class T>
struct addressof_if<true, T> {
=======
struct addrof_is_constexpr {
    static constexpr bool value = addrof_expression<T>::value &&
        !addrof_member_operator<T>::value &&
        !addrof_non_member_operator<T>::value;
};

template<bool E, class T>
struct addrof_if { };

template<class T>
struct addrof_if<true, T> {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef T* type;
};

template<class T>
BOOST_FORCEINLINE
<<<<<<< HEAD
typename addressof_if<!addressof_is_constexpr<T>::value, T>::type
addressof(T& o) BOOST_NOEXCEPT
{
    return address_of<T>::get(addressof_ref<T>(o), 0);
=======
typename addrof_if<!addrof_is_constexpr<T>::value, T>::type
addressof(T& o) BOOST_NOEXCEPT
{
    return addrof<T>::get(addrof_ref<T>(o), 0);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

template<class T>
constexpr BOOST_FORCEINLINE
<<<<<<< HEAD
typename addressof_if<addressof_is_constexpr<T>::value, T>::type
=======
typename addrof_if<addrof_is_constexpr<T>::value, T>::type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
addressof(T& o) BOOST_NOEXCEPT
{
    return &o;
}

} /* detail */

template<class T>
constexpr BOOST_FORCEINLINE T*
addressof(T& o) BOOST_NOEXCEPT
{
<<<<<<< HEAD
    return detail::addressof(o);
=======
    return boost::detail::addressof(o);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}
#endif

} /* boost */
#endif

#if !defined(BOOST_NO_CXX11_RVALUE_REFERENCES) && \
    !defined(BOOST_NO_CXX11_DELETED_FUNCTIONS)
namespace boost {

template<class T>
const T* addressof(const T&&) = delete;

} /* boost */
#endif

#endif
