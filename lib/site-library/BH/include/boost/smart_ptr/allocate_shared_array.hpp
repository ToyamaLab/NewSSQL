/*
<<<<<<< HEAD
Copyright 2012-2017 Glen Joseph Fernandes
=======
Copyright 2012-2019 Glen Joseph Fernandes
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
(glenjofe@gmail.com)

Distributed under the Boost Software License, Version 1.0.
(http://www.boost.org/LICENSE_1_0.txt)
*/
#ifndef BOOST_SMART_PTR_ALLOCATE_SHARED_ARRAY_HPP
#define BOOST_SMART_PTR_ALLOCATE_SHARED_ARRAY_HPP

<<<<<<< HEAD
#include <boost/smart_ptr/shared_ptr.hpp>
#include <boost/type_traits/alignment_of.hpp>
#include <boost/type_traits/has_trivial_assign.hpp>
#include <boost/type_traits/has_trivial_constructor.hpp>
#include <boost/type_traits/has_trivial_destructor.hpp>
=======
#include <boost/core/alloc_construct.hpp>
#include <boost/core/first_scalar.hpp>
#include <boost/smart_ptr/shared_ptr.hpp>
#include <boost/type_traits/alignment_of.hpp>
#include <boost/type_traits/enable_if.hpp>
#include <boost/type_traits/extent.hpp>
#include <boost/type_traits/is_bounded_array.hpp>
#include <boost/type_traits/is_unbounded_array.hpp>
#include <boost/type_traits/remove_cv.hpp>
#include <boost/type_traits/remove_extent.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/type_traits/type_with_alignment.hpp>

namespace boost {
namespace detail {

<<<<<<< HEAD
template<class>
struct sp_if_array { };

template<class T>
struct sp_if_array<T[]> {
    typedef boost::shared_ptr<T[]> type;
};

template<class>
struct sp_if_size_array { };

template<class T, std::size_t N>
struct sp_if_size_array<T[N]> {
    typedef boost::shared_ptr<T[N]> type;
};

template<class>
struct sp_array_element { };

template<class T>
struct sp_array_element<T[]> {
    typedef T type;
};

template<class T, std::size_t N>
struct sp_array_element<T[N]> {
    typedef T type;
};

template<class T>
struct sp_array_scalar {
    typedef T type;
};

template<class T, std::size_t N>
struct sp_array_scalar<T[N]> {
    typedef typename sp_array_scalar<T>::type type;
};

template<class T, std::size_t N>
struct sp_array_scalar<const T[N]> {
    typedef typename sp_array_scalar<T>::type type;
};

template<class T, std::size_t N>
struct sp_array_scalar<volatile T[N]> {
    typedef typename sp_array_scalar<T>::type type;
};

template<class T, std::size_t N>
struct sp_array_scalar<const volatile T[N]> {
    typedef typename sp_array_scalar<T>::type type;
};

template<class T>
struct sp_array_scalar<T[]> {
    typedef typename sp_array_scalar<T>::type type;
};

template<class T>
struct sp_array_scalar<const T[]> {
    typedef typename sp_array_scalar<T>::type type;
};

template<class T>
struct sp_array_scalar<volatile T[]> {
    typedef typename sp_array_scalar<T>::type type;
};

template<class T>
struct sp_array_scalar<const volatile T[]> {
    typedef typename sp_array_scalar<T>::type type;
=======
template<class T>
struct sp_array_element {
    typedef typename boost::remove_cv<typename
        boost::remove_extent<T>::type>::type type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

template<class T>
struct sp_array_count {
    enum {
        value = 1
    };
};

template<class T, std::size_t N>
struct sp_array_count<T[N]> {
    enum {
        value = N * sp_array_count<T>::value
    };
};

template<std::size_t N, std::size_t M>
struct sp_max_size {
    enum {
        value = N < M ? M : N
    };
};

template<std::size_t N, std::size_t M>
struct sp_align_up {
    enum {
        value = (N + M - 1) & ~(M - 1)
    };
};

#if !defined(BOOST_NO_CXX11_ALLOCATOR)
template<class A, class T>
struct sp_bind_allocator {
    typedef typename std::allocator_traits<A>::template rebind_alloc<T> type;
};
#else
template<class A, class T>
struct sp_bind_allocator {
    typedef typename A::template rebind<T>::other type;
};
#endif

template<class T>
BOOST_CONSTEXPR inline std::size_t
sp_objects(std::size_t size) BOOST_SP_NOEXCEPT
{
    return (size + sizeof(T) - 1) / sizeof(T);
}

<<<<<<< HEAD
template<bool, class = void>
struct sp_enable { };

template<class T>
struct sp_enable<true, T> {
    typedef T type;
};

template<bool E, class A, class T>
inline typename sp_enable<!E && boost::has_trivial_destructor<T>::value>::type
sp_array_destroy(A&, T*, std::size_t) BOOST_SP_NOEXCEPT { }

template<bool E, class A, class T>
inline typename sp_enable<!E &&
    !boost::has_trivial_destructor<T>::value>::type
sp_array_destroy(A&, T* start, std::size_t size)
{
    while (size > 0) {
        start[--size].~T();
    }
}

#if !defined(BOOST_NO_CXX11_ALLOCATOR)
template<bool E, class A, class T>
inline typename sp_enable<E>::type
sp_array_destroy(A& allocator, T* start, std::size_t size)
{
    while (size > 0) {
        std::allocator_traits<A>::destroy(allocator, start + --size);
    }
}
#endif

template<bool E, class A, class T>
inline typename sp_enable<!E &&
    boost::has_trivial_constructor<T>::value &&
    boost::has_trivial_assign<T>::value &&
    boost::has_trivial_destructor<T>::value>::type
sp_array_construct(A&, T* start, std::size_t size)
{
    for (std::size_t i = 0; i < size; ++i) {
        start[i] = T();
    }
}

template<bool E, class A, class T>
inline typename sp_enable<!E &&
    boost::has_trivial_constructor<T>::value &&
    boost::has_trivial_assign<T>::value &&
    boost::has_trivial_destructor<T>::value>::type
sp_array_construct(A&, T* start, std::size_t size, const T* list,
    std::size_t count)
{
    for (std::size_t i = 0; i < size; ++i) {
        start[i] = list[i % count];
    }
}

#if !defined(BOOST_NO_EXCEPTIONS)
template<bool E, class A, class T>
inline typename sp_enable<!E &&
    !(boost::has_trivial_constructor<T>::value &&
      boost::has_trivial_assign<T>::value &&
      boost::has_trivial_destructor<T>::value)>::type
sp_array_construct(A& none, T* start, std::size_t size)
{
    std::size_t i = 0;
    try {
        for (; i < size; ++i) {
            ::new(static_cast<void*>(start + i)) T();
        }
    } catch (...) {
        sp_array_destroy<E>(none, start, i);
        throw;
    }
}

template<bool E, class A, class T>
inline typename sp_enable<!E &&
    !(boost::has_trivial_constructor<T>::value &&
      boost::has_trivial_assign<T>::value &&
      boost::has_trivial_destructor<T>::value)>::type
sp_array_construct(A& none, T* start, std::size_t size, const T* list,
    std::size_t count)
{
    std::size_t i = 0;
    try {
        for (; i < size; ++i) {
            ::new(static_cast<void*>(start + i)) T(list[i % count]);
        }
    } catch (...) {
        sp_array_destroy<E>(none, start, i);
        throw;
    }
}
#else
template<bool E, class A, class T>
inline typename sp_enable<!E &&
    !(boost::has_trivial_constructor<T>::value &&
      boost::has_trivial_assign<T>::value &&
      boost::has_trivial_destructor<T>::value)>::type
sp_array_construct(A&, T* start, std::size_t size)
{
    for (std::size_t i = 0; i < size; ++i) {
        ::new(static_cast<void*>(start + i)) T();
    }
}

template<bool E, class A, class T>
inline typename sp_enable<!E &&
    !(boost::has_trivial_constructor<T>::value &&
      boost::has_trivial_assign<T>::value &&
      boost::has_trivial_destructor<T>::value)>::type
sp_array_construct(A&, T* start, std::size_t size, const T* list,
    std::size_t count)
{
    for (std::size_t i = 0; i < size; ++i) {
        ::new(static_cast<void*>(start + i)) T(list[i % count]);
    }
}
#endif

#if !defined(BOOST_NO_CXX11_ALLOCATOR)
#if !defined(BOOST_NO_EXCEPTIONS)
template<bool E, class A, class T>
inline typename sp_enable<E>::type
sp_array_construct(A& allocator, T* start, std::size_t size)
{
    std::size_t i = 0;
    try {
        for (i = 0; i < size; ++i) {
            std::allocator_traits<A>::construct(allocator, start + i);
        }
    } catch (...) {
        sp_array_destroy<E>(allocator, start, i);
        throw;
    }
}

template<bool E, class A, class T>
inline typename sp_enable<E>::type
sp_array_construct(A& allocator, T* start, std::size_t size, const T* list,
    std::size_t count)
{
    std::size_t i = 0;
    try {
        for (i = 0; i < size; ++i) {
            std::allocator_traits<A>::construct(allocator, start + i,
                list[i % count]);
        }
    } catch (...) {
        sp_array_destroy<E>(allocator, start, i);
        throw;
    }
}
#else
template<bool E, class A, class T>
inline typename sp_enable<E>::type
sp_array_construct(A& allocator, T* start, std::size_t size)
{
    for (std::size_t i = 0; i < size; ++i) {
        std::allocator_traits<A>::construct(allocator, start + i);
    }
}

template<bool E, class A, class T>
inline typename sp_enable<E>::type
sp_array_construct(A& allocator, T* start, std::size_t size, const T* list,
    std::size_t count)
{
    for (std::size_t i = 0; i < size; ++i) {
        std::allocator_traits<A>::construct(allocator, start + i,
            list[i % count]);
    }
}
#endif
#endif

template<class A, class T>
inline typename sp_enable<boost::has_trivial_constructor<T>::value>::type
sp_array_default(A&, T*, std::size_t) BOOST_SP_NOEXCEPT { }

#if !defined(BOOST_NO_EXCEPTIONS)
template<class A, class T>
inline typename sp_enable<!boost::has_trivial_constructor<T>::value>::type
sp_array_default(A& none, T* start, std::size_t size)
{
    std::size_t i = 0;
    try {
        for (; i < size; ++i) {
            ::new(static_cast<void*>(start + i)) T;
        }
    } catch (...) {
        sp_array_destroy<false>(none, start, i);
        throw;
    }
}
#else
template<bool E, class A, class T>
inline typename sp_enable<!boost::has_trivial_constructor<T>::value>::type
sp_array_default(A&, T* start, std::size_t size)
{
    for (std::size_t i = 0; i < size; ++i) {
        ::new(static_cast<void*>(start + i)) T;
    }
}
#endif

=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
template<class A>
class sp_array_state {
public:
    typedef A type;

    template<class U>
<<<<<<< HEAD
    sp_array_state(const U& allocator, std::size_t size) BOOST_SP_NOEXCEPT
        : allocator_(allocator),
          size_(size) { }
=======
    sp_array_state(const U& _allocator, std::size_t _size) BOOST_SP_NOEXCEPT
        : allocator_(_allocator),
          size_(_size) { }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

    A& allocator() BOOST_SP_NOEXCEPT {
        return allocator_;
    }

    std::size_t size() const BOOST_SP_NOEXCEPT {
        return size_;
    }

private:
    A allocator_;
    std::size_t size_;
};

template<class A, std::size_t N>
class sp_size_array_state {
public:
    typedef A type;

    template<class U>
<<<<<<< HEAD
    sp_size_array_state(const U& allocator, std::size_t) BOOST_SP_NOEXCEPT
        : allocator_(allocator) { }
=======
    sp_size_array_state(const U& _allocator, std::size_t) BOOST_SP_NOEXCEPT
        : allocator_(_allocator) { }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

    A& allocator() BOOST_SP_NOEXCEPT {
        return allocator_;
    }

    BOOST_CONSTEXPR std::size_t size() const BOOST_SP_NOEXCEPT {
        return N;
    }

private:
    A allocator_;
};

<<<<<<< HEAD
#if !defined(BOOST_NO_CXX11_ALLOCATOR)
template<class A>
struct sp_use_construct {
    enum {
        value = true
    };
};

template<class T>
struct sp_use_construct<std::allocator<T> > {
    enum {
        value = false
    };
};
#else
template<class>
struct sp_use_construct {
    enum {
        value = false
    };
};
#endif

=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
template<class T, class U>
struct sp_array_alignment {
    enum {
        value = sp_max_size<boost::alignment_of<T>::value,
            boost::alignment_of<U>::value>::value
    };
};

template<class T, class U>
struct sp_array_offset {
    enum {
        value = sp_align_up<sizeof(T), sp_array_alignment<T, U>::value>::value
    };
};

<<<<<<< HEAD
template<class T, class U>
struct sp_array_storage {
    enum {
        value = sp_array_alignment<T, U>::value
    };
    typedef typename boost::type_with_alignment<value>::type type;
};

template<class T, class U>
inline U*
sp_array_start(void* base) BOOST_SP_NOEXCEPT
=======
template<class U, class T>
inline U*
sp_array_start(T* base) BOOST_SP_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    enum {
        size = sp_array_offset<T, U>::value
    };
<<<<<<< HEAD
    return reinterpret_cast<U*>(static_cast<char*>(base) + size);
=======
    return reinterpret_cast<U*>(reinterpret_cast<char*>(base) + size);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

template<class A, class T>
class sp_array_creator {
<<<<<<< HEAD
    typedef typename A::value_type scalar;

    enum {
        offset = sp_array_offset<T, scalar>::value
    };

    typedef typename sp_array_storage<T, scalar>::type type;
=======
    typedef typename A::value_type element;

    enum {
        offset = sp_array_offset<T, element>::value
    };

    typedef typename boost::type_with_alignment<sp_array_alignment<T,
        element>::value>::type type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

public:
    template<class U>
    sp_array_creator(const U& other, std::size_t size) BOOST_SP_NOEXCEPT
        : other_(other),
<<<<<<< HEAD
          size_(sp_objects<type>(offset + sizeof(scalar) * size)) { }
=======
          size_(sp_objects<type>(offset + sizeof(element) * size)) { }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

    T* create() {
        return reinterpret_cast<T*>(other_.allocate(size_));
    }

    void destroy(T* base) {
        other_.deallocate(reinterpret_cast<type*>(base), size_);
    }

private:
    typename sp_bind_allocator<A, type>::type other_;
    std::size_t size_;
};

<<<<<<< HEAD
struct sp_default { };

template<class T, bool E = sp_use_construct<T>::value>
class sp_array_base
=======
template<class T>
class BOOST_SYMBOL_VISIBLE sp_array_base
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    : public sp_counted_base {
    typedef typename T::type allocator;

public:
    typedef typename allocator::value_type type;

    template<class A>
<<<<<<< HEAD
    sp_array_base(const A& other, std::size_t size, type* start)
        : state_(other, size) {
        sp_array_construct<E>(state_.allocator(), start, state_.size());
    }

    template<class A>
    sp_array_base(const A& other, std::size_t size, const type* list,
        std::size_t count, type* start)
        : state_(other, size) {
        sp_array_construct<E>(state_.allocator(), start, state_.size(), list,
            count);
    }

    template<class A>
    sp_array_base(sp_default, const A& other, std::size_t size, type* start)
        : state_(other, size) {
        sp_array_default(state_.allocator(), start, state_.size());
=======
    sp_array_base(const A& other, type* start, std::size_t size)
        : state_(other, size) {
        boost::alloc_construct_n(state_.allocator(),
            boost::first_scalar(start),
            state_.size() * sp_array_count<type>::value);
    }

    template<class A, class U>
    sp_array_base(const A& other, type* start, std::size_t size, const U& list)
        : state_(other, size) {
        enum {
            count = sp_array_count<type>::value
        };
        boost::alloc_construct_n(state_.allocator(),
            boost::first_scalar(start), state_.size() * count,
            boost::first_scalar(&list), count);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }

    T& state() BOOST_SP_NOEXCEPT {
        return state_;
    }

<<<<<<< HEAD
    virtual void dispose() {
        sp_array_destroy<E>(state_.allocator(),
            sp_array_start<sp_array_base, type>(this), state_.size());
    }

    virtual void destroy() {
=======
    virtual void dispose() BOOST_SP_NOEXCEPT {
        boost::alloc_destroy_n(state_.allocator(),
            boost::first_scalar(sp_array_start<type>(this)),
            state_.size() * sp_array_count<type>::value);
    }

    virtual void destroy() BOOST_SP_NOEXCEPT {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        sp_array_creator<allocator, sp_array_base> other(state_.allocator(),
            state_.size());
        this->~sp_array_base();
        other.destroy(this);
    }

<<<<<<< HEAD
    virtual void* get_deleter(const sp_typeinfo&) {
        return 0;
    }

    virtual void* get_local_deleter(const sp_typeinfo&) {
        return 0;
    }

    virtual void* get_untyped_deleter() {
=======
    virtual void* get_deleter(const sp_typeinfo_&) BOOST_SP_NOEXCEPT {
        return 0;
    }

    virtual void* get_local_deleter(const sp_typeinfo_&) BOOST_SP_NOEXCEPT {
        return 0;
    }

    virtual void* get_untyped_deleter() BOOST_SP_NOEXCEPT {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        return 0;
    }

private:
    T state_;
};

template<class A, class T>
struct sp_array_result {
public:
    template<class U>
    sp_array_result(const U& other, std::size_t size)
        : creator_(other, size),
          result_(creator_.create()) { }

    ~sp_array_result() {
        if (result_) {
            creator_.destroy(result_);
        }
    }

<<<<<<< HEAD
    T* get() const {
        return result_;
    }

    void release() {
=======
    T* get() const BOOST_SP_NOEXCEPT {
        return result_;
    }

    void release() BOOST_SP_NOEXCEPT {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        result_ = 0;
    }

private:
    sp_array_result(const sp_array_result&);
    sp_array_result& operator=(const sp_array_result&);

    sp_array_creator<A, T> creator_;
    T* result_;
};

} /* detail */

template<class T, class A>
<<<<<<< HEAD
inline typename detail::sp_if_array<T>::type
allocate_shared(const A& allocator, std::size_t count)
{
    typedef typename detail::sp_array_element<T>::type type;
    typedef typename detail::sp_array_scalar<T>::type scalar;
    typedef typename detail::sp_bind_allocator<A, scalar>::type other;
    typedef detail::sp_array_state<other> state;
    typedef detail::sp_array_base<state> base;
    std::size_t size = count * detail::sp_array_count<type>::value;
    detail::sp_array_result<other, base> result(allocator, size);
    detail::sp_counted_base* node = result.get();
    scalar* start = detail::sp_array_start<base, scalar>(node);
    ::new(static_cast<void*>(node)) base(allocator, size, start);
    result.release();
    return shared_ptr<T>(detail::sp_internal_constructor_tag(),
        reinterpret_cast<type*>(start), detail::shared_count(node));
}

template<class T, class A>
inline typename detail::sp_if_size_array<T>::type
allocate_shared(const A& allocator)
{
    enum {
        size = detail::sp_array_count<T>::value
    };
    typedef typename detail::sp_array_element<T>::type type;
    typedef typename detail::sp_array_scalar<T>::type scalar;
    typedef typename detail::sp_bind_allocator<A, scalar>::type other;
    typedef detail::sp_size_array_state<other, size> state;
    typedef detail::sp_array_base<state> base;
    detail::sp_array_result<other, base> result(allocator, size);
    detail::sp_counted_base* node = result.get();
    scalar* start = detail::sp_array_start<base, scalar>(node);
    ::new(static_cast<void*>(node)) base(allocator, size, start);
    result.release();
    return shared_ptr<T>(detail::sp_internal_constructor_tag(),
        reinterpret_cast<type*>(start), detail::shared_count(node));
}

template<class T, class A>
inline typename detail::sp_if_array<T>::type
allocate_shared(const A& allocator, std::size_t count,
    const typename detail::sp_array_element<T>::type& value)
{
    typedef typename detail::sp_array_element<T>::type type;
    typedef typename detail::sp_array_scalar<T>::type scalar;
    typedef typename detail::sp_bind_allocator<A, scalar>::type other;
    typedef detail::sp_array_state<other> state;
    typedef detail::sp_array_base<state> base;
    std::size_t size = count * detail::sp_array_count<type>::value;
    detail::sp_array_result<other, base> result(allocator, size);
    detail::sp_counted_base* node = result.get();
    scalar* start = detail::sp_array_start<base, scalar>(node);
    ::new(static_cast<void*>(node)) base(allocator, size,
        reinterpret_cast<const scalar*>(&value),
        detail::sp_array_count<type>::value, start);
    result.release();
    return shared_ptr<T>(detail::sp_internal_constructor_tag(),
        reinterpret_cast<type*>(start), detail::shared_count(node));
}

template<class T, class A>
inline typename detail::sp_if_size_array<T>::type
allocate_shared(const A& allocator,
    const typename detail::sp_array_element<T>::type& value)
{
    enum {
        size = detail::sp_array_count<T>::value
    };
    typedef typename detail::sp_array_element<T>::type type;
    typedef typename detail::sp_array_scalar<T>::type scalar;
    typedef typename detail::sp_bind_allocator<A, scalar>::type other;
    typedef detail::sp_size_array_state<other, size> state;
    typedef detail::sp_array_base<state> base;
    detail::sp_array_result<other, base> result(allocator, size);
    detail::sp_counted_base* node = result.get();
    scalar* start = detail::sp_array_start<base, scalar>(node);
    ::new(static_cast<void*>(node)) base(allocator, size,
        reinterpret_cast<const scalar*>(&value),
        detail::sp_array_count<type>::value, start);
    result.release();
    return shared_ptr<T>(detail::sp_internal_constructor_tag(),
        reinterpret_cast<type*>(start), detail::shared_count(node));
}

template<class T, class A>
inline typename detail::sp_if_array<T>::type
allocate_shared_noinit(const A& allocator, std::size_t count)
{
    typedef typename detail::sp_array_element<T>::type type;
    typedef typename detail::sp_array_scalar<T>::type scalar;
    typedef typename detail::sp_bind_allocator<A, scalar>::type other;
    typedef detail::sp_array_state<other> state;
    typedef detail::sp_array_base<state, false> base;
    std::size_t size = count * detail::sp_array_count<type>::value;
    detail::sp_array_result<other, base> result(allocator, size);
    detail::sp_counted_base* node = result.get();
    scalar* start = detail::sp_array_start<base, scalar>(node);
    ::new(static_cast<void*>(node)) base(detail::sp_default(), allocator,
        size, start);
    result.release();
    return shared_ptr<T>(detail::sp_internal_constructor_tag(),
        reinterpret_cast<type*>(start), detail::shared_count(node));
}

template<class T, class A>
inline typename detail::sp_if_size_array<T>::type
allocate_shared_noinit(const A& allocator)
{
    enum {
        size = detail::sp_array_count<T>::value
    };
    typedef typename detail::sp_array_element<T>::type type;
    typedef typename detail::sp_array_scalar<T>::type scalar;
    typedef typename detail::sp_bind_allocator<A, scalar>::type other;
    typedef detail::sp_size_array_state<other, size> state;
    typedef detail::sp_array_base<state, false> base;
    detail::sp_array_result<other, base> result(allocator, size);
    detail::sp_counted_base* node = result.get();
    scalar* start = detail::sp_array_start<base, scalar>(node);
    ::new(static_cast<void*>(node)) base(detail::sp_default(), allocator,
        size, start);
    result.release();
    return shared_ptr<T>(detail::sp_internal_constructor_tag(),
        reinterpret_cast<type*>(start), detail::shared_count(node));
=======
inline typename enable_if_<is_unbounded_array<T>::value, shared_ptr<T> >::type
allocate_shared(const A& allocator, std::size_t count)
{
    typedef typename detail::sp_array_element<T>::type element;
    typedef typename detail::sp_bind_allocator<A, element>::type other;
    typedef detail::sp_array_state<other> state;
    typedef detail::sp_array_base<state> base;
    detail::sp_array_result<other, base> result(allocator, count);
    base* node = result.get();
    element* start = detail::sp_array_start<element>(node);
    ::new(static_cast<void*>(node)) base(allocator, start, count);
    result.release();
    return shared_ptr<T>(detail::sp_internal_constructor_tag(), start,
        detail::shared_count(static_cast<detail::sp_counted_base*>(node)));
}

template<class T, class A>
inline typename enable_if_<is_bounded_array<T>::value, shared_ptr<T> >::type
allocate_shared(const A& allocator)
{
    enum {
        count = extent<T>::value
    };
    typedef typename detail::sp_array_element<T>::type element;
    typedef typename detail::sp_bind_allocator<A, element>::type other;
    typedef detail::sp_size_array_state<other, extent<T>::value> state;
    typedef detail::sp_array_base<state> base;
    detail::sp_array_result<other, base> result(allocator, count);
    base* node = result.get();
    element* start = detail::sp_array_start<element>(node);
    ::new(static_cast<void*>(node)) base(allocator, start, count);
    result.release();
    return shared_ptr<T>(detail::sp_internal_constructor_tag(), start,
        detail::shared_count(static_cast<detail::sp_counted_base*>(node)));
}

template<class T, class A>
inline typename enable_if_<is_unbounded_array<T>::value, shared_ptr<T> >::type
allocate_shared(const A& allocator, std::size_t count,
    const typename remove_extent<T>::type& value)
{
    typedef typename detail::sp_array_element<T>::type element;
    typedef typename detail::sp_bind_allocator<A, element>::type other;
    typedef detail::sp_array_state<other> state;
    typedef detail::sp_array_base<state> base;
    detail::sp_array_result<other, base> result(allocator, count);
    base* node = result.get();
    element* start = detail::sp_array_start<element>(node);
    ::new(static_cast<void*>(node)) base(allocator, start, count, value);
    result.release();
    return shared_ptr<T>(detail::sp_internal_constructor_tag(), start,
        detail::shared_count(static_cast<detail::sp_counted_base*>(node)));
}

template<class T, class A>
inline typename enable_if_<is_bounded_array<T>::value, shared_ptr<T> >::type
allocate_shared(const A& allocator,
    const typename remove_extent<T>::type& value)
{
    enum {
        count = extent<T>::value
    };
    typedef typename detail::sp_array_element<T>::type element;
    typedef typename detail::sp_bind_allocator<A, element>::type other;
    typedef detail::sp_size_array_state<other, extent<T>::value> state;
    typedef detail::sp_array_base<state> base;
    detail::sp_array_result<other, base> result(allocator, count);
    base* node = result.get();
    element* start = detail::sp_array_start<element>(node);
    ::new(static_cast<void*>(node)) base(allocator, start, count, value);
    result.release();
    return shared_ptr<T>(detail::sp_internal_constructor_tag(), start,
        detail::shared_count(static_cast<detail::sp_counted_base*>(node)));
}

template<class T, class A>
inline typename enable_if_<is_unbounded_array<T>::value, shared_ptr<T> >::type
allocate_shared_noinit(const A& allocator, std::size_t count)
{
    return boost::allocate_shared<T>(boost::noinit_adapt(allocator), count);
}

template<class T, class A>
inline typename enable_if_<is_bounded_array<T>::value, shared_ptr<T> >::type
allocate_shared_noinit(const A& allocator)
{
    return boost::allocate_shared<T>(boost::noinit_adapt(allocator));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

} /* boost */

#endif
