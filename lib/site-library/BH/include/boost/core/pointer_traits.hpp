/*
<<<<<<< HEAD
Copyright 2017 Glen Joseph Fernandes
=======
Copyright 2017-2018 Glen Joseph Fernandes
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
(glenjofe@gmail.com)

Distributed under the Boost Software License, Version 1.0.
(http://www.boost.org/LICENSE_1_0.txt)
*/
#ifndef BOOST_CORE_POINTER_TRAITS_HPP
#define BOOST_CORE_POINTER_TRAITS_HPP

#include <boost/config.hpp>
#if !defined(BOOST_NO_CXX11_POINTER_TRAITS)
#include <memory>
#else
#include <boost/core/addressof.hpp>
#endif

namespace boost {

<<<<<<< HEAD
template<class T>
struct pointer_traits;

namespace detail {

template<class U>
inline typename boost::pointer_traits<U>::element_type*
ptr_traits_address(const U& v) BOOST_NOEXCEPT
{
    return boost::pointer_traits<U>::to_address(v);
}

} /* detail */

=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#if !defined(BOOST_NO_CXX11_POINTER_TRAITS)
template<class T>
struct pointer_traits
    : std::pointer_traits<T> {
    template<class U>
    struct rebind_to {
        typedef typename std::pointer_traits<T>::template rebind<U> type;
    };
<<<<<<< HEAD
    static typename std::pointer_traits<T>::element_type*
    to_address(const T& v) BOOST_NOEXCEPT {
        return detail::ptr_traits_address(v.operator->());
    }
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

template<class T>
struct pointer_traits<T*>
    : std::pointer_traits<T*> {
    template<class U>
    struct rebind_to {
        typedef U* type;
    };
<<<<<<< HEAD
    static T* to_address(T* v) BOOST_NOEXCEPT {
        return v;
    }
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};
#else
namespace detail {

<<<<<<< HEAD
struct ptr_traits_none { char first, second; };

template<class T>
struct ptr_traits_has_element {
private:
    template<class U>
    static ptr_traits_none call(...);
    template<class U>
    static char call(typename U::element_type* = 0);
public:
    static const bool value = sizeof(call<T>(0)) == 1;
};

template<class T>
struct ptr_traits_first;

#if !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
template<template<class, class...> class T, class U, class... Args>
struct ptr_traits_first<T<U, Args...> > {
=======
template<class>
struct ptr_void {
    typedef void type;
};

template<class T>
struct ptr_first;

#if !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
template<template<class, class...> class T, class U, class... Args>
struct ptr_first<T<U, Args...> > {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef U type;
};
#else
template<template<class> class T, class U>
<<<<<<< HEAD
struct ptr_traits_first<T<U> > {
=======
struct ptr_first<T<U> > {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef U type;
};

template<template<class, class> class T, class U1, class U2>
<<<<<<< HEAD
struct ptr_traits_first<T<U1, U2> > {
=======
struct ptr_first<T<U1, U2> > {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef U1 type;
};

template<template<class, class, class> class T, class U1, class U2, class U3>
<<<<<<< HEAD
struct ptr_traits_first<T<U1, U2, U3> > {
=======
struct ptr_first<T<U1, U2, U3> > {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef U1 type;
};
#endif

<<<<<<< HEAD
template<class T, bool = ptr_traits_has_element<T>::value>
struct ptr_traits_element {
    typedef typename T::element_type type;
};

template<class T>
struct ptr_traits_element<T, false> {
    typedef typename ptr_traits_first<T>::type type;
};

template<class T>
struct ptr_traits_has_difference {
private:
    template<class U>
    static ptr_traits_none call(...);
    template<class U>
    static char call(typename U::difference_type* = 0);
public:
    static const bool value = sizeof(call<T>(0)) == 1;
};

template<class T, bool = ptr_traits_has_difference<T>::value>
struct ptr_traits_difference {
    typedef typename T::difference_type type;
};

template<class T>
struct ptr_traits_difference<T, false> {
    typedef std::ptrdiff_t type;
};

template<class T, class V>
struct ptr_traits_has_rebind {
private:
    template<class U>
    static ptr_traits_none call(...);
    template<class U>
    static char call(typename U::template rebind<V>* = 0);
public:
    static const bool value = sizeof(call<T>(0)) == 1;
};

template<class T, class V>
struct ptr_traits_rebind_to;

#if !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
template<template<class, class...> class T, class U, class... Args, class V>
struct ptr_traits_rebind_to<T<U, Args...>, V> {
=======
template<class T, class = void>
struct ptr_element {
    typedef typename ptr_first<T>::type type;
};

template<class T>
struct ptr_element<T, typename ptr_void<typename T::element_type>::type> {
    typedef typename T::element_type type;
};

template<class, class = void>
struct ptr_difference {
    typedef std::ptrdiff_t type;
};

template<class T>
struct ptr_difference<T,
    typename ptr_void<typename T::difference_type>::type> {
    typedef typename T::difference_type type;
};

template<class T, class V>
struct ptr_transform;

#if !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
template<template<class, class...> class T, class U, class... Args, class V>
struct ptr_transform<T<U, Args...>, V> {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef T<V, Args...> type;
};
#else
template<template<class> class T, class U, class V>
<<<<<<< HEAD
struct ptr_traits_rebind_to<T<U>, V> {
=======
struct ptr_transform<T<U>, V> {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef T<V> type;
};

template<template<class, class> class T, class U1, class U2, class V>
<<<<<<< HEAD
struct ptr_traits_rebind_to<T<U1, U2>, V> {
=======
struct ptr_transform<T<U1, U2>, V> {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef T<V, U2> type;
};

template<template<class, class, class> class T,
    class U1, class U2, class U3, class V>
<<<<<<< HEAD
struct ptr_traits_rebind_to<T<U1, U2, U3>, V> {
=======
struct ptr_transform<T<U1, U2, U3>, V> {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef T<V, U2, U3> type;
};
#endif

<<<<<<< HEAD
#if !defined(BOOST_NO_CXX11_TEMPLATE_ALIASES)
template<class T, class U, bool = ptr_traits_has_rebind<T, U>::value>
struct ptr_traits_rebind {
    typedef typename T::template rebind<U> type;
};

template<class T, class U>
struct ptr_traits_rebind<T, U, false> {
    typedef typename ptr_traits_rebind_to<T, U>::type type;
};
#else
template<class T, class U>
struct ptr_traits_rebind {
    typedef typename ptr_traits_rebind_to<T, U>::type type;
=======
template<class T, class U, class = void>
struct ptr_rebind {
    typedef typename ptr_transform<T, U>::type type;
};

#if !defined(BOOST_NO_CXX11_TEMPLATE_ALIASES)
template<class T, class U>
struct ptr_rebind<T, U,
    typename ptr_void<typename T::template rebind<U> >::type> {
    typedef typename T::template rebind<U> type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};
#endif

template<class T>
<<<<<<< HEAD
struct ptr_traits_value {
=======
struct ptr_value {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef T type;
};

template<>
<<<<<<< HEAD
struct ptr_traits_value<void> {
=======
struct ptr_value<void> {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef struct { } type;
};

} /* detail */

template<class T>
struct pointer_traits {
    typedef T pointer;
<<<<<<< HEAD
    typedef typename detail::ptr_traits_element<T>::type element_type;
    typedef typename detail::ptr_traits_difference<T>::type difference_type;
    template<class U>
    struct rebind_to {
        typedef typename detail::ptr_traits_rebind<T, U>::type type;
    };
#if !defined(BOOST_NO_CXX11_TEMPLATE_ALIASES)
    template<class U>
    using rebind = typename detail::ptr_traits_rebind<T, U>::type;
#endif
    static pointer
    pointer_to(typename detail::ptr_traits_value<element_type>::type& v) {
        return pointer::pointer_to(v);
    }
    static element_type* to_address(const pointer& v) BOOST_NOEXCEPT {
        return detail::ptr_traits_address(v.operator->());
    }
=======
    typedef typename detail::ptr_element<T>::type element_type;
    typedef typename detail::ptr_difference<T>::type difference_type;
    template<class U>
    struct rebind_to {
        typedef typename detail::ptr_rebind<T, U>::type type;
    };
#if !defined(BOOST_NO_CXX11_TEMPLATE_ALIASES)
    template<class U>
    using rebind = typename detail::ptr_rebind<T, U>::type;
#endif
    static pointer
    pointer_to(typename detail::ptr_value<element_type>::type& v) {
        return pointer::pointer_to(v);
    }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

template<class T>
struct pointer_traits<T*> {
    typedef T* pointer;
    typedef T element_type;
    typedef std::ptrdiff_t difference_type;
    template<class U>
    struct rebind_to {
        typedef U* type;
    };
#if !defined(BOOST_NO_CXX11_TEMPLATE_ALIASES)
    template<class U>
    using rebind = U*;
#endif
    static T*
<<<<<<< HEAD
    pointer_to(typename detail::ptr_traits_value<T>::type& v) BOOST_NOEXCEPT {
        return addressof(v);
    }
    static T* to_address(T* v) BOOST_NOEXCEPT {
        return v;
=======
    pointer_to(typename detail::ptr_value<T>::type& v) BOOST_NOEXCEPT {
        return boost::addressof(v);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
};
#endif

template<class T>
<<<<<<< HEAD
inline typename pointer_traits<T>::element_type*
to_address(const T& v) BOOST_NOEXCEPT
{
    return pointer_traits<T>::to_address(v);
}

template<class T>
inline T*
to_address(T* v) BOOST_NOEXCEPT
=======
BOOST_CONSTEXPR inline T*
to_address(T* v) BOOST_NOEXCEPT
{
    return v;
}

#if !defined(BOOST_NO_CXX14_RETURN_TYPE_DEDUCTION)
namespace detail {

template<class T>
inline T*
ptr_address(T* v, int) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    return v;
}

<<<<<<< HEAD
=======
template<class T>
inline auto
ptr_address(const T& v, int) BOOST_NOEXCEPT
-> decltype(boost::pointer_traits<T>::to_address(v))
{
    return boost::pointer_traits<T>::to_address(v);
}

template<class T>
inline auto
ptr_address(const T& v, long) BOOST_NOEXCEPT
{
    return boost::detail::ptr_address(v.operator->(), 0);
}

} /* detail */

template<class T>
inline auto
to_address(const T& v) BOOST_NOEXCEPT
{
    return boost::detail::ptr_address(v, 0);
}
#else
template<class T>
inline typename pointer_traits<T>::element_type*
to_address(const T& v) BOOST_NOEXCEPT
{
    return boost::to_address(v.operator->());
}
#endif

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
} /* boost */

#endif
