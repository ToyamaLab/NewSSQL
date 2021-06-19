
//  Copyright 2012 John Maddock. Distributed under the Boost
//  Software License, Version 1.0. (See accompanying file
<<<<<<< HEAD
//  LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_
=======
//  LICENSE_1_0.txt or copy at https://www.boost.org/LICENSE_1_0.txt
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#ifndef BOOST_MP_CPP_INT_CHECKED_HPP
#define BOOST_MP_CPP_INT_CHECKED_HPP

<<<<<<< HEAD
namespace boost{ namespace multiprecision{ namespace backends{ namespace detail{
=======
namespace boost { namespace multiprecision { namespace backends { namespace detail {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

//
// Simple routines for performing checked arithmetic with a builtin arithmetic type.
// Note that this is not a complete header, it must be included as part of boost/multiprecision/cpp_int.hpp.
//

inline void raise_overflow(std::string op)
{
   BOOST_THROW_EXCEPTION(std::overflow_error("overflow in " + op));
}
inline void raise_add_overflow()
{
   raise_overflow("addition");
}
inline void raise_subtract_overflow()
{
   BOOST_THROW_EXCEPTION(std::range_error("Subtraction resulted in a negative value, but the type is unsigned"));
}
inline void raise_mul_overflow()
{
   raise_overflow("multiplication");
}
inline void raise_div_overflow()
{
   raise_overflow("division");
}

template <class A>
<<<<<<< HEAD
inline A checked_add_imp(A a, A b, const mpl::true_&)
{
   if(a > 0)
   {
      if((b > 0) && ((integer_traits<A>::const_max - b) < a))
=======
inline BOOST_MP_CXX14_CONSTEXPR A checked_add_imp(A a, A b, const mpl::true_&)
{
   if (a > 0)
   {
      if ((b > 0) && ((integer_traits<A>::const_max - b) < a))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         raise_add_overflow();
   }
   else
   {
<<<<<<< HEAD
      if((b < 0) && ((integer_traits<A>::const_min - b) > a))
=======
      if ((b < 0) && ((integer_traits<A>::const_min - b) > a))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         raise_add_overflow();
   }
   return a + b;
}
template <class A>
<<<<<<< HEAD
inline A checked_add_imp(A a, A b, const mpl::false_&)
{
   if((integer_traits<A>::const_max - b) < a)
=======
inline BOOST_MP_CXX14_CONSTEXPR A checked_add_imp(A a, A b, const mpl::false_&)
{
   if ((integer_traits<A>::const_max - b) < a)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      raise_add_overflow();
   return a + b;
}
template <class A>
<<<<<<< HEAD
inline A checked_add(A a, A b, const mpl::int_<checked>&)
{
   return checked_add_imp(a, b, boost::is_signed<A>());
}
template <class A>
inline A checked_add(A a, A b, const mpl::int_<unchecked>&)
=======
inline BOOST_MP_CXX14_CONSTEXPR A checked_add(A a, A b, const mpl::int_<checked>&)
{
   return checked_add_imp(a, b, mpl::bool_<boost::is_signed<A>::value>());
}
template <class A>
inline BOOST_MP_CXX14_CONSTEXPR A checked_add(A a, A b, const mpl::int_<unchecked>&)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return a + b;
}

template <class A>
<<<<<<< HEAD
inline A checked_subtract_imp(A a, A b, const mpl::true_&)
{
   if(a > 0)
   {
      if((b < 0) && ((integer_traits<A>::const_max + b) < a))
=======
inline BOOST_MP_CXX14_CONSTEXPR A checked_subtract_imp(A a, A b, const mpl::true_&)
{
   if (a > 0)
   {
      if ((b < 0) && ((integer_traits<A>::const_max + b) < a))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         raise_subtract_overflow();
   }
   else
   {
<<<<<<< HEAD
      if((b > 0) && ((integer_traits<A>::const_min + b) > a))
=======
      if ((b > 0) && ((integer_traits<A>::const_min + b) > a))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         raise_subtract_overflow();
   }
   return a - b;
}
template <class A>
<<<<<<< HEAD
inline A checked_subtract_imp(A a, A b, const mpl::false_&)
{
   if(a < b)
=======
inline BOOST_MP_CXX14_CONSTEXPR A checked_subtract_imp(A a, A b, const mpl::false_&)
{
   if (a < b)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      raise_subtract_overflow();
   return a - b;
}
template <class A>
<<<<<<< HEAD
inline A checked_subtract(A a, A b, const mpl::int_<checked>&)
{
   return checked_subtract_imp(a, b, boost::is_signed<A>());
}
template <class A>
inline A checked_subtract(A a, A b, const mpl::int_<unchecked>&)
=======
inline BOOST_MP_CXX14_CONSTEXPR A checked_subtract(A a, A b, const mpl::int_<checked>&)
{
   return checked_subtract_imp(a, b, mpl::bool_<boost::is_signed<A>::value>());
}
template <class A>
inline BOOST_MP_CXX14_CONSTEXPR A checked_subtract(A a, A b, const mpl::int_<unchecked>&)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return a - b;
}

template <class A>
<<<<<<< HEAD
inline A checked_multiply(A a, A b, const mpl::int_<checked>&)
{
   BOOST_MP_USING_ABS
   if(a && (integer_traits<A>::const_max / abs(a) < abs(b)))
=======
inline BOOST_MP_CXX14_CONSTEXPR A checked_multiply(A a, A b, const mpl::int_<checked>&)
{
   BOOST_MP_USING_ABS
   if (a && (integer_traits<A>::const_max / abs(a) < abs(b)))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      raise_mul_overflow();
   return a * b;
}
template <class A>
<<<<<<< HEAD
inline A checked_multiply(A a, A b, const mpl::int_<unchecked>&)
=======
inline BOOST_MP_CXX14_CONSTEXPR A checked_multiply(A a, A b, const mpl::int_<unchecked>&)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return a * b;
}

template <class A>
<<<<<<< HEAD
inline A checked_divide(A a, A b, const mpl::int_<checked>&)
{
   if(b == 0)
=======
inline BOOST_MP_CXX14_CONSTEXPR A checked_divide(A a, A b, const mpl::int_<checked>&)
{
   if (b == 0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
      raise_div_overflow();
   return a / b;
}
template <class A>
<<<<<<< HEAD
inline A checked_divide(A a, A b, const mpl::int_<unchecked>&)
=======
inline BOOST_MP_CXX14_CONSTEXPR A checked_divide(A a, A b, const mpl::int_<unchecked>&)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return a / b;
}

template <class A>
<<<<<<< HEAD
inline A checked_left_shift(A a, boost::ulong_long_type shift, const mpl::int_<checked>&)
{
   if(a && shift)
   {
      if((shift > sizeof(A) * CHAR_BIT) || (a >> (sizeof(A) * CHAR_BIT - shift)))
=======
inline BOOST_MP_CXX14_CONSTEXPR A checked_left_shift(A a, boost::ulong_long_type shift, const mpl::int_<checked>&)
{
   if (a && shift)
   {
      if ((shift > sizeof(A) * CHAR_BIT) || (a >> (sizeof(A) * CHAR_BIT - shift)))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
         BOOST_THROW_EXCEPTION(std::overflow_error("Shift out of range"));
   }
   return a << shift;
}
template <class A>
<<<<<<< HEAD
inline A checked_left_shift(A a, boost::ulong_long_type shift, const mpl::int_<unchecked>&)
=======
inline BOOST_MP_CXX14_CONSTEXPR A checked_left_shift(A a, boost::ulong_long_type shift, const mpl::int_<unchecked>&)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
   return (shift >= sizeof(A) * CHAR_BIT) ? 0 : a << shift;
}

<<<<<<< HEAD
}}}} // namespaces

#endif

=======
}}}} // namespace boost::multiprecision::backends::detail

#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
