// Copyright David Abrahams 2002.
// Distributed under the Boost Software License, Version 1.0. (See
// accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)
#ifndef INDIRECT_TRAITS_DWA2002131_HPP
# define INDIRECT_TRAITS_DWA2002131_HPP
<<<<<<< HEAD
=======
# include <boost/type_traits/integral_constant.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
# include <boost/type_traits/is_function.hpp>
# include <boost/type_traits/is_reference.hpp>
# include <boost/type_traits/is_pointer.hpp>
# include <boost/type_traits/is_class.hpp>
# include <boost/type_traits/is_const.hpp>
# include <boost/type_traits/is_volatile.hpp>
# include <boost/type_traits/is_member_function_pointer.hpp>
# include <boost/type_traits/is_member_pointer.hpp>
# include <boost/type_traits/remove_cv.hpp>
# include <boost/type_traits/remove_reference.hpp>
# include <boost/type_traits/remove_pointer.hpp>

# include <boost/detail/workaround.hpp>
<<<<<<< HEAD

# include <boost/mpl/eval_if.hpp>
# include <boost/mpl/if.hpp>
# include <boost/mpl/bool.hpp>
# include <boost/mpl/and.hpp>
# include <boost/mpl/not.hpp>
# include <boost/mpl/aux_/lambda_support.hpp>
=======
# include <boost/detail/select_type.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce


namespace boost { namespace detail {

namespace indirect_traits {

template <class T>
<<<<<<< HEAD
struct is_reference_to_const : mpl::false_
=======
struct is_reference_to_const : boost::false_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

template <class T>
<<<<<<< HEAD
struct is_reference_to_const<T const&> : mpl::true_
=======
struct is_reference_to_const<T const&> : boost::true_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

#   if defined(BOOST_MSVC) && _MSC_FULL_VER <= 13102140 // vc7.01 alpha workaround
template<class T>
<<<<<<< HEAD
struct is_reference_to_const<T const volatile&> : mpl::true_
{
};
#   endif 

template <class T>
struct is_reference_to_function : mpl::false_
=======
struct is_reference_to_const<T const volatile&> : boost::true_type
{
};
#   endif

template <class T>
struct is_reference_to_function : boost::false_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

template <class T>
struct is_reference_to_function<T&> : is_function<T>
{
};

template <class T>
<<<<<<< HEAD
struct is_pointer_to_function : mpl::false_
=======
struct is_pointer_to_function : boost::false_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

// There's no such thing as a pointer-to-cv-function, so we don't need
// specializations for those
template <class T>
struct is_pointer_to_function<T*> : is_function<T>
{
};

template <class T>
<<<<<<< HEAD
struct is_reference_to_member_function_pointer_impl : mpl::false_
=======
struct is_reference_to_member_function_pointer_impl : boost::false_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

template <class T>
struct is_reference_to_member_function_pointer_impl<T&>
    : is_member_function_pointer<typename remove_cv<T>::type>
{
};


template <class T>
struct is_reference_to_member_function_pointer
    : is_reference_to_member_function_pointer_impl<T>
{
<<<<<<< HEAD
    BOOST_MPL_AUX_LAMBDA_SUPPORT(1,is_reference_to_member_function_pointer,(T))
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

template <class T>
struct is_reference_to_function_pointer_aux
<<<<<<< HEAD
    : mpl::and_<
          is_reference<T>
        , is_pointer_to_function<
              typename remove_cv<
                  typename remove_reference<T>::type
              >::type
          >
=======
    : boost::integral_constant<bool,
          is_reference<T>::value &&
          is_pointer_to_function<
              typename remove_cv<
                  typename remove_reference<T>::type
              >::type
          >::value
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      >
{
    // There's no such thing as a pointer-to-cv-function, so we don't need specializations for those
};

template <class T>
struct is_reference_to_function_pointer
<<<<<<< HEAD
    : mpl::if_<
          is_reference_to_function<T>
        , mpl::false_
        , is_reference_to_function_pointer_aux<T>
     >::type
=======
    : boost::detail::if_true<
          is_reference_to_function<T>::value
      >::template then<
          boost::false_type
        , is_reference_to_function_pointer_aux<T>
      >::type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

template <class T>
struct is_reference_to_non_const
<<<<<<< HEAD
    : mpl::and_<
          is_reference<T>
        , mpl::not_<
             is_reference_to_const<T>
          >
=======
    : boost::integral_constant<bool,
          is_reference<T>::value &&
          !is_reference_to_const<T>::value
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      >
{
};

template <class T>
<<<<<<< HEAD
struct is_reference_to_volatile : mpl::false_
=======
struct is_reference_to_volatile : boost::false_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

template <class T>
<<<<<<< HEAD
struct is_reference_to_volatile<T volatile&> : mpl::true_
=======
struct is_reference_to_volatile<T volatile&> : boost::true_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

#   if defined(BOOST_MSVC) && _MSC_FULL_VER <= 13102140 // vc7.01 alpha workaround
template <class T>
<<<<<<< HEAD
struct is_reference_to_volatile<T const volatile&> : mpl::true_
{
};
#   endif 


template <class T>
struct is_reference_to_pointer : mpl::false_
=======
struct is_reference_to_volatile<T const volatile&> : boost::true_type
{
};
#   endif


template <class T>
struct is_reference_to_pointer : boost::false_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

template <class T>
<<<<<<< HEAD
struct is_reference_to_pointer<T*&> : mpl::true_
=======
struct is_reference_to_pointer<T*&> : boost::true_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

template <class T>
<<<<<<< HEAD
struct is_reference_to_pointer<T* const&> : mpl::true_
=======
struct is_reference_to_pointer<T* const&> : boost::true_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

template <class T>
<<<<<<< HEAD
struct is_reference_to_pointer<T* volatile&> : mpl::true_
=======
struct is_reference_to_pointer<T* volatile&> : boost::true_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

template <class T>
<<<<<<< HEAD
struct is_reference_to_pointer<T* const volatile&> : mpl::true_
=======
struct is_reference_to_pointer<T* const volatile&> : boost::true_type
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
};

template <class T>
struct is_reference_to_class
<<<<<<< HEAD
    : mpl::and_<
          is_reference<T>
        , is_class<
              typename remove_cv<
                  typename remove_reference<T>::type
              >::type
          >
      >
{
    BOOST_MPL_AUX_LAMBDA_SUPPORT(1,is_reference_to_class,(T))
=======
    : boost::integral_constant<bool,
          is_reference<T>::value &&
          is_class<
              typename remove_cv<
                  typename remove_reference<T>::type
              >::type
          >::value
      >
{
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

template <class T>
struct is_pointer_to_class
<<<<<<< HEAD
    : mpl::and_<
          is_pointer<T>
        , is_class<
              typename remove_cv<
                  typename remove_pointer<T>::type
              >::type
          >
      >
{
    BOOST_MPL_AUX_LAMBDA_SUPPORT(1,is_pointer_to_class,(T))
=======
    : boost::integral_constant<bool,
          is_pointer<T>::value &&
          is_class<
              typename remove_cv<
                  typename remove_pointer<T>::type
              >::type
          >::value
      >
{
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};


}

using namespace indirect_traits;

}} // namespace boost::python::detail

#endif // INDIRECT_TRAITS_DWA2002131_HPP
