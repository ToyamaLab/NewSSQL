/*
<<<<<<< HEAD
Copyright 2012-2015 Glen Joseph Fernandes
=======
Copyright 2012-2019 Glen Joseph Fernandes
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
(glenjofe@gmail.com)

Distributed under the Boost Software License, Version 1.0.
(http://www.boost.org/LICENSE_1_0.txt)
*/
#ifndef BOOST_SMART_PTR_MAKE_UNIQUE_HPP
#define BOOST_SMART_PTR_MAKE_UNIQUE_HPP

<<<<<<< HEAD
#include <boost/config.hpp>
=======
#include <boost/type_traits/enable_if.hpp>
#include <boost/type_traits/is_array.hpp>
#include <boost/type_traits/is_unbounded_array.hpp>
#include <boost/type_traits/remove_extent.hpp>
#include <boost/type_traits/remove_reference.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <memory>
#include <utility>

namespace boost {
<<<<<<< HEAD
namespace detail {

template<class T>
struct up_if_object {
    typedef std::unique_ptr<T> type;
};

template<class T>
struct up_if_object<T[]> { };

template<class T, std::size_t N>
struct up_if_object<T[N]> { };

template<class T>
struct up_if_array { };

template<class T>
struct up_if_array<T[]> {
    typedef std::unique_ptr<T[]> type;
};

template<class T>
struct up_remove_reference {
    typedef T type;
};

template<class T>
struct up_remove_reference<T&> {
    typedef T type;
};

template<class T>
struct up_remove_reference<T&&> {
    typedef T type;
};

template<class T>
struct up_element { };

template<class T>
struct up_element<T[]> {
    typedef T type;
};

} /* detail */

template<class T>
inline typename detail::up_if_object<T>::type
=======

template<class T>
inline typename enable_if_<!is_array<T>::value, std::unique_ptr<T> >::type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
make_unique()
{
    return std::unique_ptr<T>(new T());
}

#if !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
template<class T, class... Args>
<<<<<<< HEAD
inline typename detail::up_if_object<T>::type
=======
inline typename enable_if_<!is_array<T>::value, std::unique_ptr<T> >::type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
make_unique(Args&&... args)
{
    return std::unique_ptr<T>(new T(std::forward<Args>(args)...));
}
#endif

template<class T>
<<<<<<< HEAD
inline typename detail::up_if_object<T>::type
make_unique(typename detail::up_remove_reference<T>::type&& value)
=======
inline typename enable_if_<!is_array<T>::value, std::unique_ptr<T> >::type
make_unique(typename remove_reference<T>::type&& value)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    return std::unique_ptr<T>(new T(std::move(value)));
}

template<class T>
<<<<<<< HEAD
inline typename detail::up_if_object<T>::type
=======
inline typename enable_if_<!is_array<T>::value, std::unique_ptr<T> >::type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
make_unique_noinit()
{
    return std::unique_ptr<T>(new T);
}

template<class T>
<<<<<<< HEAD
inline typename detail::up_if_array<T>::type
make_unique(std::size_t size)
{
    return std::unique_ptr<T>(new typename
        detail::up_element<T>::type[size]());
}

template<class T>
inline typename detail::up_if_array<T>::type
make_unique_noinit(std::size_t size)
{
    return std::unique_ptr<T>(new typename
        detail::up_element<T>::type[size]);
=======
inline typename enable_if_<is_unbounded_array<T>::value,
    std::unique_ptr<T> >::type
make_unique(std::size_t size)
{
    return std::unique_ptr<T>(new typename remove_extent<T>::type[size]());
}

template<class T>
inline typename enable_if_<is_unbounded_array<T>::value,
    std::unique_ptr<T> >::type
make_unique_noinit(std::size_t size)
{
    return std::unique_ptr<T>(new typename remove_extent<T>::type[size]);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

} /* boost */

#endif
