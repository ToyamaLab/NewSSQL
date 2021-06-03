/*
Copyright 2017 Peter Dimov
<<<<<<< HEAD
Copyright 2017 Glen Joseph Fernandes
=======
Copyright 2017-2019 Glen Joseph Fernandes
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
(glenjofe@gmail.com)

Distributed under the Boost Software License, Version 1.0.
(http://www.boost.org/LICENSE_1_0.txt)
*/
#ifndef BOOST_SMART_PTR_MAKE_LOCAL_SHARED_ARRAY_HPP
#define BOOST_SMART_PTR_MAKE_LOCAL_SHARED_ARRAY_HPP

<<<<<<< HEAD
=======
#include <boost/core/default_allocator.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/smart_ptr/allocate_local_shared_array.hpp>

namespace boost {

template<class T>
<<<<<<< HEAD
inline typename detail::lsp_if_size_array<T>::type
make_local_shared()
{
    return boost::allocate_local_shared<T>(std::allocator<typename
        detail::sp_array_scalar<T>::type>());
}

template<class T>
inline typename detail::lsp_if_size_array<T>::type
make_local_shared(const typename detail::sp_array_element<T>::type& value)
{
    return boost::allocate_local_shared<T>(std::allocator<typename
        detail::sp_array_scalar<T>::type>(), value);
}

template<class T>
inline typename detail::lsp_if_array<T>::type
make_local_shared(std::size_t size)
{
    return boost::allocate_local_shared<T>(std::allocator<typename
        detail::sp_array_scalar<T>::type>(), size);
}

template<class T>
inline typename detail::lsp_if_array<T>::type
make_local_shared(std::size_t size,
    const typename detail::sp_array_element<T>::type& value)
{
    return boost::allocate_local_shared<T>(std::allocator<typename
        detail::sp_array_scalar<T>::type>(), size, value);
}

template<class T>
inline typename detail::lsp_if_size_array<T>::type
make_local_shared_noinit()
{
    return allocate_local_shared_noinit<T>(std::allocator<typename
        detail::sp_array_scalar<T>::type>());
}

template<class T>
inline typename detail::lsp_if_array<T>::type
make_local_shared_noinit(std::size_t size)
{
    return allocate_local_shared_noinit<T>(std::allocator<typename
        detail::sp_array_scalar<T>::type>(), size);
=======
inline typename enable_if_<is_bounded_array<T>::value,
    local_shared_ptr<T> >::type
make_local_shared()
{
    return boost::allocate_local_shared<T>(boost::default_allocator<typename
        detail::sp_array_element<T>::type>());
}

template<class T>
inline typename enable_if_<is_bounded_array<T>::value,
    local_shared_ptr<T> >::type
make_local_shared(const typename remove_extent<T>::type& value)
{
    return boost::allocate_local_shared<T>(boost::default_allocator<typename
        detail::sp_array_element<T>::type>(), value);
}

template<class T>
inline typename enable_if_<is_unbounded_array<T>::value,
    local_shared_ptr<T> >::type
make_local_shared(std::size_t size)
{
    return boost::allocate_local_shared<T>(boost::default_allocator<typename
        detail::sp_array_element<T>::type>(), size);
}

template<class T>
inline typename enable_if_<is_unbounded_array<T>::value,
    local_shared_ptr<T> >::type
make_local_shared(std::size_t size,
    const typename remove_extent<T>::type& value)
{
    return boost::allocate_local_shared<T>(boost::default_allocator<typename
        detail::sp_array_element<T>::type>(), size, value);
}

template<class T>
inline typename enable_if_<is_bounded_array<T>::value,
    local_shared_ptr<T> >::type
make_local_shared_noinit()
{
    return boost::allocate_local_shared_noinit<T>(boost::
        default_allocator<typename detail::sp_array_element<T>::type>());
}

template<class T>
inline typename enable_if_<is_unbounded_array<T>::value,
    local_shared_ptr<T> >::type
make_local_shared_noinit(std::size_t size)
{
    return boost::allocate_local_shared_noinit<T>(boost::
        default_allocator<typename detail::sp_array_element<T>::type>(), size);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

} /* boost */

#endif
