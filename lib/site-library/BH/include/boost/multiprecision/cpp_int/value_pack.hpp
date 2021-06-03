///////////////////////////////////////////////////////////////
//  Copyright 2013 John Maddock. Distributed under the Boost
//  Software License, Version 1.0. (See accompanying file
<<<<<<< HEAD
//  LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_
=======
//  LICENSE_1_0.txt or copy at https://www.boost.org/LICENSE_1_0.txt
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#ifndef BOOST_MP_CPP_INT_VP_HPP
#define BOOST_MP_CPP_INT_VP_HPP

<<<<<<< HEAD
namespace boost{ namespace multiprecision{

namespace literals{ namespace detail{

template <limb_type...VALUES>
struct value_pack
{
   constexpr value_pack(){}
=======
namespace boost {
namespace multiprecision {

namespace literals { namespace detail {

template <limb_type... VALUES>
struct value_pack
{
   constexpr value_pack() {}
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

   typedef value_pack<0, VALUES...> next_type;
};
template <class T>
<<<<<<< HEAD
struct is_value_pack{ static constexpr bool value = false; };
template <limb_type...VALUES>
struct is_value_pack<value_pack<VALUES...> >{ static constexpr bool value = true; };

struct negate_tag{};
=======
struct is_value_pack
{
   static constexpr bool value = false;
};
template <limb_type... VALUES>
struct is_value_pack<value_pack<VALUES...> >
{
   static constexpr bool value = true;
};

struct negate_tag
{};
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

constexpr negate_tag make_negate_tag()
{
   return negate_tag();
}

<<<<<<< HEAD

}}}} // namespaces

#endif // BOOST_MP_CPP_INT_CORE_HPP

=======
}}}} // namespace boost::multiprecision::literals::detail

#endif // BOOST_MP_CPP_INT_CORE_HPP
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
